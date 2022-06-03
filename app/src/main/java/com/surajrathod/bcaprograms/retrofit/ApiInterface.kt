package com.surajrathod.bcaprograms.retrofit

import com.surajrathod.bcaprograms.model.RemoteProgram
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    //https://desolate-ocean-39685.herokuapp.com/v1/programs
    @GET("programs")
    fun getData() : Call<List<RemoteProgram>>

    @Headers("Content-Type: application/json")
    @POST("programs/add")
    suspend fun add(@Body program : RemoteProgram) : SimpleResponse



    //https://desolate-ocean-39685.herokuapp.com/v1/programs/specific?sem=sem 1&sub=dbms&unit=Unit - 4
    @GET("programs/specific")
    fun getSpecific(@Query("sem") sem : String,
    @Query("sub") sub : String,
                    @Query("unit") unit : String

                    ) : Call<List<RemoteProgram>>


}
