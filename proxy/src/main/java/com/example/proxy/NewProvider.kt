package com.example.proxy

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.base.models.New
import kotlinx.coroutines.flow.Flow

class NewProvider (
    private val database: AppDatabase,
    private val newService: NewService
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getAllNews(): Pager<Int, New> {
        val pagingSourceFactory:()-> PagingSource<Int, New> = {
            database.newDao().queryAll()
        }

        return Pager(
            config = PagingConfig(pageSize = 50),
            initialKey = null,
            remoteMediator = NewRemoteMediator(database, newService),
            pagingSourceFactory = pagingSourceFactory
        )
    }
}