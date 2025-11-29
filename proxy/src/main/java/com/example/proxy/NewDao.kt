package com.example.proxy

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.base.models.New

@Dao
interface NewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<New>)

    @Query("select * from news")
    fun queryAll(): PagingSource<Int, New>

    @Query("DELETE FROM news")
    suspend fun deleteAll()
}