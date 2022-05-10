package com.surajrathod.bcaprograms.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.surajrathod.bcaprograms.data.Program
import com.surajrathod.bcaprograms.data.ProgramDao
import com.surajrathod.bcaprograms.repository.ProgramRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActvityViewModel (val programDao : ProgramDao) : ViewModel() {

    val name = "suraj"

    fun addProgram(program : Program) = viewModelScope.launch(Dispatchers.IO) {

        programDao.addProgram(program)

    }

    fun getAllPrograms() : List<Program> = programDao.getAllPrograms()


    fun getSpecificProgram(sem : String,sub : String,unit : String) : Flow<List<Program>> = programDao.getSpecificProgram(sem,sub,unit)


}

class MainActivityViewModelFactory(val programDao: ProgramDao) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActvityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActvityViewModel(programDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}