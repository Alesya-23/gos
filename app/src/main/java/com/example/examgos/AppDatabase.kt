package com.example.examgos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Film::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            val templateInstance = INSTANCE
            if(templateInstance !=null){
                return templateInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, "MyDatabase").allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }

    }
}