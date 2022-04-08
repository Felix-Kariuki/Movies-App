package com.flexcode.movie.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flexcode.movie.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        var INSTANCE: MovieDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MovieDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie.db"
                ).allowMainThreadQueries().build()
            }//allow access db in main thread is not recommended run in background
            return INSTANCE as MovieDatabase
        }
    }

}