package com.surajrathod.bcaprograms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.surajrathod.bcaprograms.data.PDatabase
import com.surajrathod.bcaprograms.data.ProgramEntity
import com.surajrathod.bcaprograms.model.RemoteProgram
import com.surajrathod.bcaprograms.repository.PRepo
import com.surajrathod.bcaprograms.retrofit.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PViewModel(application: Application):AndroidViewModel(application) {


    val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://desolate-ocean-39685.herokuapp.com/v1/")
        .build().create(ApiInterface::class.java)



    val BASE_URL = "https://desolate-ocean-39685.herokuapp.com/v1/"

    lateinit var body : List<RemoteProgram>
    lateinit var king : String
    val getAllProgram : LiveData<List<ProgramEntity>>
    val repo : PRepo

    init {
        val pDao = PDatabase.getDatabase(application).getDao()
        repo = PRepo(pDao)
        getAllProgram = repo.readAllData

    }

    fun getSpecific(sem : String, sub : String,  unit:String) : LiveData<List<ProgramEntity>>
    {
        return repo.getSpecificData(sem,sub,unit)

    }


    fun addProgram(p : ProgramEntity)
    {
        viewModelScope.launch(Dispatchers.IO) {

            repo.addProgram(p)
        }
    }

    fun addPrograms(p : RemoteProgram) = viewModelScope.launch(Dispatchers.IO) {


        retrofitBuilder.add(p)


    }



    fun getData() : List<RemoteProgram>
    {



        //var body : List<RemoteProgram>
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://desolate-ocean-39685.herokuapp.com/v1/")
            .build().create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<RemoteProgram>?> {
            override fun onResponse(
                call: Call<List<RemoteProgram>?>,
                response: Response<List<RemoteProgram>?>
            ) {

                 body = response.body()!!



            }

            override fun onFailure(call: Call<List<RemoteProgram>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })


        return body

    }
}