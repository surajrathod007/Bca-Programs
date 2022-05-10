package com.surajrathod.bcaprograms.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajrathod.bcaprograms.ProgramApplication
import com.surajrathod.bcaprograms.R
import com.surajrathod.bcaprograms.adapter.ProgramAdapter
import com.surajrathod.bcaprograms.data.Program
import com.surajrathod.bcaprograms.data.ProgramDatabase
import com.surajrathod.bcaprograms.databinding.ActivityMainBinding
import com.surajrathod.bcaprograms.model.RemoteProgram
import com.surajrathod.bcaprograms.repository.ProgramRepo
import com.surajrathod.bcaprograms.retrofit.ApiInterface
import com.surajrathod.bcaprograms.viewmodel.MainActivityViewModelFactory
import com.surajrathod.bcaprograms.viewmodel.MainActvityViewModel
import com.surajrathod.bcaprograms.viewmodel.PViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var db : ProgramDatabase

    val adapter = ProgramAdapter(){}
    lateinit var king : String
    lateinit var pViewModel : PViewModel
    val viewModel : MainActvityViewModel by viewModels{
        MainActivityViewModelFactory(ProgramApplication().database.getProgramDao())
    }


    override fun onResume() {
        getData()
        rvProgram.layoutManager = LinearLayoutManager(this)
        rvProgram.adapter = adapter
        super.onResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
        pViewModel = ViewModelProvider(this).get(PViewModel::class.java)

       // pViewModel.getData()
        //Toast.makeText(this,"${pViewModel.king}",Toast.LENGTH_LONG).show()

//       db = ProgramDatabase.invoke(this)
      val list : List<RemoteProgram> = listOf(RemoteProgram(1,"s","s","s","s","s"))
//


//        pViewModel.getAllProgram.observe(this, Observer {
//            adapter.submitList(it)
//        })

    //     adapter.submitList(list)



//        adapter.submitList(list1)

//
//        var l1 = emptyList<Program>()
//
//        db.getProgramDao().getAllPrograms().observe(this, Observer { program->
//
//             l1 = program
//        })
//
//
//
//        supportActionBar?.title = l1[0].title.toString()

//        lifecycle.coroutineScope.launch {
//            viewModel.getAllPrograms().collect(){
//                adapter.submitList(it)
//            }
//        }





        setupActionBar()





    }

    fun setupActionBar()
    {
        setSupportActionBar(customToolBar)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.addProgram ->{

                startActivity(Intent(this,AddProgramActivity::class.java))

            }
        }
         return super.onOptionsItemSelected(item)

    }


    fun getData()
    {
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

                val body = response.body()!!

                if(body.isEmpty()){
                    Toast.makeText(this@MainActivity,"failed",Toast.LENGTH_LONG).show()

                }
//                king = body[0].title.toString()
//                Toast.makeText(this@MainActivity,"$king",Toast.LENGTH_LONG).show()

                adapter.submitList(body)


            }

            override fun onFailure(call: Call<List<RemoteProgram>?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"failed",Toast.LENGTH_LONG).show()
            }
        })

    }
}