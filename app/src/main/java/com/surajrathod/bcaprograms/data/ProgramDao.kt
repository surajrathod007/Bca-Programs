package com.surajrathod.bcaprograms.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ProgramDao {

    @Insert
    suspend fun addProgram(program : Program)


    @Query("select * from Program")
    fun getAllPrograms() : List<Program>


    @Query("select * from Program where sem = :sem and sub = :sub and unit = :unit")
    fun getSpecificProgram(sem : String,sub : String,unit : String):Flow<List<Program>>


}