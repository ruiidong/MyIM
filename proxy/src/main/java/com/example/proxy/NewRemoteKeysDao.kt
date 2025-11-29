package com.example.proxy

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.base.models.NewRemoteKey

@Dao
interface NewRemoteKeysDao {
    @Query("SELECT * FROM newRemoteKeys WHERE uniqueKey = :uniqueKey")
    suspend fun findByUniqueKey(uniqueKey: String): NewRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllKeys(remoteKeys:List<NewRemoteKey>)

    @Query("DELETE FROM newRemoteKeys")
    suspend fun deleteAllKeys()
}