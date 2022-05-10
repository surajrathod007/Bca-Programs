package com.surajrathod.bcaprograms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.surajrathod.bcaprograms.ProgramApplication
import com.surajrathod.bcaprograms.R
import com.surajrathod.bcaprograms.adapter.ProgramAdapter
import com.surajrathod.bcaprograms.data.Program
import com.surajrathod.bcaprograms.data.ProgramDatabase
import com.surajrathod.bcaprograms.data.ProgramEntity
import com.surajrathod.bcaprograms.model.RemoteProgram
import com.surajrathod.bcaprograms.repository.ProgramRepo
import com.surajrathod.bcaprograms.retrofit.ApiInterface
import com.surajrathod.bcaprograms.viewmodel.MainActivityViewModelFactory
import com.surajrathod.bcaprograms.viewmodel.MainActvityViewModel
import com.surajrathod.bcaprograms.viewmodel.PViewModel
import kotlinx.android.synthetic.main.activity_add_program.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class AddProgramActivity : AppCompatActivity() {



    lateinit var pViewModel : PViewModel
    val viewModel : MainActvityViewModel by viewModels{
        MainActivityViewModelFactory(ProgramApplication().database.getProgramDao())
    }

    lateinit var sb : ProgramDatabase

    val adapter = ProgramAdapter(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_program)


        pViewModel = ViewModelProvider(this).get(PViewModel::class.java)


        btnEnterView.setOnClickListener {
            //recyclerView(semSpinner.selectedItem.toString(),subjectSpinner.selectedItem.toString(),unitSpinner.selectedItem.toString())

            getData()
            rvViewProgram.layoutManager = LinearLayoutManager(this@AddProgramActivity)
            rvViewProgram.adapter = adapter
        }


        sb = ProgramDatabase.invoke(this)
        val repo = ProgramRepo(sb)
    val sem = arrayOf<String>("Sem 1","Sem 2","Sem 3","Sem 4","Sem 5","Sem 6",)
    val semAdapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sem)



    val unit = arrayOf("Unit 1","Unit 2","Unit 3","Unit 4",)
    val unitAdapter = ArrayAdapter(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,unit)


    val sem1Subject = arrayOf("C Program","Html")
    val sem1Sub = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sem1Subject)

    val sem2Subject = arrayOf("Dbms","Html/Js")
    val sem2Sub = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sem2Subject)

    val sem3Subject = arrayOf("C++","Acp")
    val sem3Sub = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sem3Subject)

    val sem4Subject = arrayOf("C#","Java","Dbms 2")
    val sem4Sub = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sem4Subject)

    val sem5Subject = arrayOf("Python","Asp.net")
    val sem5Sub = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sem5Subject)

    val sem6Subject = arrayOf("Web Application Dev")
    val sem6Sub = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sem6Subject)

    semSpinner.adapter = semAdapter

    unitSpinner.adapter = unitAdapter

    semSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

            Toast.makeText(this@AddProgramActivity,sem[position],Toast.LENGTH_SHORT).show()

            when(position)
            {
                0->{
                    subjectSpinner.adapter = sem1Sub
                }

                1->{
                    subjectSpinner.adapter = sem2Sub
                }

                2->{
                    subjectSpinner.adapter = sem3Sub
                }

                3->{
                    subjectSpinner.adapter = sem4Sub
                }

                4->{
                    subjectSpinner.adapter = sem5Sub
                }

                5->{
                    subjectSpinner.adapter = sem6Sub
                }
            }
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            TODO("Not yet implemented")
        }

    }

        btnEnter.setOnClickListener {
            txtResult.text = "${semSpinner.selectedItem.toString()} : ${subjectSpinner.selectedItem.toString()} : ${unitSpinner.selectedItem.toString()}"


            val p = RemoteProgram(edId.text.toString().toInt(),edtitle.text.toString(),edprogram.text.toString(),semSpinner.selectedItem.toString(),subjectSpinner.selectedItem.toString(),unitSpinner.selectedItem.toString())

            pViewModel.addPrograms(p)
           // pViewModel.addProgram(p)
//RemoteProgram(9,"s","s","s","s","s",)


//            txtResult.text = viewModel.name
//            GlobalScope.launch(Dispatchers.IO) {
//                repo.addProgram(Program(
//                    0,edtitle.text.toString(),edprogram.text.toString(),semSpinner.selectedItem.toString(),subjectSpinner.selectedItem.toString(),unitSpinner.selectedItem.toString()
//                ))
//            }


//
//                viewModel.addProgram(
//                    Program(
//                        0,edtitle.text.toString(),edprogram.text.toString(),semSpinner.selectedItem.toString(),subjectSpinner.selectedItem.toString(),unitSpinner.selectedItem.toString()
//                ))








            //addPrograms()
            Toast.makeText(this,"Program added",Toast.LENGTH_SHORT).show()


        }





    }

    fun recyclerView(sem: String, sub: String, unit: String) {

//        pViewModel.getSpecific(sem,sub,unit).observe(this@AddProgramActivity, Observer {
//            adapter.submitList(it)
//        })

        rvViewProgram.layoutManager = LinearLayoutManager(this@AddProgramActivity)
        rvViewProgram.adapter = adapter

    }

     fun addPrograms()
    {
//        val retrofitBuilder = Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://desolate-ocean-39685.herokuapp.com/v1/")
//            .build()
//
//        val placeholder = retrofitBuilder.create(ApiInterface::class.java)

        val p = RemoteProgram(edId.text.toString().toInt(),edtitle.text.toString(),edprogram.text.toString(),semSpinner.selectedItem.toString(),subjectSpinner.selectedItem.toString(),unitSpinner.selectedItem.toString())


        val s = RemoteProgram(89,"s","s","s","s","s",)

        //pViewModel.addPrograms(s)





    }


    fun getData()
    {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://desolate-ocean-39685.herokuapp.com/v1/")
            .build().create(ApiInterface::class.java)



        val retrofitData = retrofitBuilder.getSpecific(semSpinner.selectedItem.toString(),subjectSpinner.selectedItem.toString(),unitSpinner.selectedItem.toString())

        retrofitData.enqueue(object : Callback<List<RemoteProgram>?> {
            override fun onResponse(
                call: Call<List<RemoteProgram>?>,
                response: Response<List<RemoteProgram>?>
            ) {
                val list = response.body()

                adapter.submitList(list)
            }

            override fun onFailure(call: Call<List<RemoteProgram>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


}