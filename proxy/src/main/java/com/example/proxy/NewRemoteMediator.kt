package com.example.proxy

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.base.models.New
import com.example.base.models.NewRemoteKey
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class NewRemoteMediator(
    private val database: AppDatabase,
    private val newService: NewService
) : RemoteMediator<Int, New>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, New>
    ): MediatorResult {
        return try {
            /**
             *  从数据库获取缓存的当前页面
             */
            val curPage = when (loadType) {
                //UI初始化刷新
                LoadType.REFRESH -> 1

                //在当前列表头添加数据使用
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)

                //尾部加载更多的记录
                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForTail(state)
                    val nextPage =
                        remoteKey?.nextPage ?: return MediatorResult.Success(remoteKey != null)
                    nextPage
                }
            }

            /**
             * 联网状态下的处理
             * 获取网络资源
             * response
             */
            val response = newService.getNews(apiKey = "441e71258d7fbd6f5e46c88017ac1e02", page = curPage, pageSize = 50).result?.newsList?:emptyList()
            val endOfPaginationReached = response.isEmpty()

            val prePage = if (curPage == 1) null else curPage - 1
            val nextPage = if (endOfPaginationReached) null else curPage + 1

            database.withTransaction {
                //刷新记录，需要删除原有的记录
                if (loadType == LoadType.REFRESH) {
                    database.newDao().deleteAll()
                    database.newRemoteKeysDao().deleteAllKeys()
                }

                //获取的记录映射成对应的索引记录
                val keys: List<NewRemoteKey> = response.map { new: New ->
                    NewRemoteKey(new.uniqueKey, prePage, nextPage)
                }

                database.newRemoteKeysDao().insertAllKeys(keys)
                database.newDao().insertAll(response)
            }

            MediatorResult.Success(endOfPaginationReached)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForTail(state: PagingState<Int, New>): NewRemoteKey? =
        state.pages.lastOrNull { it ->
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { new: New ->
            database.newRemoteKeysDao().findByUniqueKey(new.uniqueKey)
        }
}