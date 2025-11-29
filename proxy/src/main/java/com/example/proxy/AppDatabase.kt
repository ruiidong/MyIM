package com.example.proxy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.base.models.New
import com.example.base.models.NewRemoteKey

@Database(version = 1, entities = [New::class, NewRemoteKey::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun newDao(): NewDao
    abstract fun newRemoteKeysDao(): NewRemoteKeysDao

    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "appDB.db"
            ).build().apply {
                instance = this
            }
        }
    }
}