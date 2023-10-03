package com.example.proyecto_2.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyecto_2.R
import com.example.proyecto_2.controllers.DetailSuperheroActivity.Companion.EXTRA_ID
import com.example.proyecto_2.adapter.SuperheroAdapter
import com.example.proyecto_2.database.AppDatabase
import com.example.proyecto_2.databinding.ActivitySuperHeroListBinding
import com.example.proyecto_2.models.Hero
import com.example.proyecto_2.response.SuperHeroDataResponse
import com.example.proyecto_2.service.ApiService
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding:ActivitySuperHeroListBinding
    private lateinit var retrofit:Retrofit
    private lateinit var adapter:SuperheroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit= getRetrofit()
        initUI()


    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?)=false

        })

        adapter = SuperheroAdapter(
            onItemSelected = { superheroId -> navigateToDetail(superheroId) },
            onSaveToDB = { superheroName, superheroImage -> saveToDB(superheroName, superheroImage) }
        )
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager=LinearLayoutManager(this)
        binding.rvSuperhero.adapter=adapter
    }

    private fun saveToDB(superheroName: String, superheroImage: String) {
        val hero = Hero(null,superheroName, superheroImage) // Crea un objeto Hero con los datos proporcionados
        AppDatabase.getInstance(this).getDao().insert(hero) // Inserta el héroe en la base de datos
    }


    private fun searchByName(query:String){
        binding.progressBar.isVisible=true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse:Response<SuperHeroDataResponse> = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if(myResponse.isSuccessful){
                val response:SuperHeroDataResponse? = myResponse.body()
                if(response!=null){
                        Log.i("SuperHeroListActivity",response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superheroes)
                        binding.progressBar.isVisible=false
                    }
                }
            }else{
                Log.i("SuperHeroListActivity","No funciona")
            }
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
        val intent=Intent(this,DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }
}