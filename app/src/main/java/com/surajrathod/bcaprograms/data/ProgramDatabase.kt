package com.surajrathod.bcaprograms.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(
    entities = [Program::class],
    version = 1,
)
abstract class ProgramDatabase : RoomDatabase() {

    abstract fun getProgramDao() : ProgramDao


    companion object
    {
        @Volatile
        private var instance : ProgramDatabase? = null
        private val LOCK = Any()  //only one thread can use this database at one time


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){

            instance ?: createDatabase(context).also {
                instance = it
            }
        }


        private fun createDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,
            ProgramDatabase::class.java,
            "program_database"
        ).build()

    }
}