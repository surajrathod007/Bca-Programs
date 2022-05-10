package com.surajrathod.bcaprograms.repository

import androidx.lifecycle.LiveData
import com.surajrathod.bcaprograms.data.Program
import com.surajrathod.bcaprograms.data.ProgramDatabase
import kotlinx.coroutines.flow.Flow

class ProgramRepo(private val db : ProgramDatabase) {


    suspend fun addProgram(program: Program) = db.getProgramDao().addProgram(program)

    fun getAllProgram():List<Program> = db.getProgramDao().getAllPrograms()

    fun getSpecificProgram(sem : String,sub : String,unit : String):Flow<List<Program>> = db.getProgramDao().getSpecificProgram(sem,sub,unit)
}