package com.example.proyecto_2.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_2.databinding.ItemSuperheroBinding
import com.example.proyecto_2.response.SuperHeroItemResponse
import com.squareup.picasso.Picasso

class SuperheroViewHolder (view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(
        superHeroItemResponse: SuperHeroItemResponse,
        onItemSelected: (String) -> Unit,
        onSaveToDB: ((String, String) -> Unit)){
        binding.tvSuperheroName.text=superHeroItemResponse.name

        Picasso.get().load(superHeroItemResponse.superheroImage.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.superheroId)}

        binding.btnAgregarBD.setOnClickListener { onSaveToDB(superHeroItemResponse.name, superHeroItemResponse.superheroImage.url)}
    }


}