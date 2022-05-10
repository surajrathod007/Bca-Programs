package com.surajrathod.bcaprograms.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProgramEntity::class],
version = 1,
exportSchema = false)
abstract class PDatabase  : RoomDatabase(){

    abstract fun getDao() : PDao

    companion object {
        @Volatile
        private var INSTANCE: PDatabase? = null

        fun getDatabase(context: Context): PDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null)
            {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PDatabase::class.java,
                    "p_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }
}