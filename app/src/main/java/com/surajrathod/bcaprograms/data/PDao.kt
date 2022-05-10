package com.surajrathod.bcaprograms.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface PDao {

    @Insert
    suspend fun addProgram(program : ProgramEntity)

    @Query("select * from program_table")
    fun getAllProgram(): LiveData<List<ProgramEntity>>

    @Query("select * from program_table where sem = :sem and sub = :sub and unit = :unit")
    fun getSpecificProgram(sem : String,sub : String,unit : String): LiveData<List<ProgramEntity>>


}