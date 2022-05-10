package com.surajrathod.bcaprograms.repository

import androidx.lifecycle.LiveData
import com.surajrathod.bcaprograms.data.PDao
import com.surajrathod.bcaprograms.data.ProgramEntity

class PRepo(val pDao : PDao) {

    val readAllData : LiveData<List<ProgramEntity>> = pDao.getAllProgram()

    suspend fun addProgram(p : ProgramEntity)
    {
        pDao.addProgram(p)
    }

    fun getSpecificData(sem : String,  sub : String,  unit : String):LiveData<List<ProgramEntity>> = pDao.getSpecificProgram(sem,sub,unit)


}