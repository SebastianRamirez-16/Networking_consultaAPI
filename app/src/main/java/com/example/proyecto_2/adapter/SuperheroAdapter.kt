package com.example.proyecto_2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_2.R
import com.example.proyecto_2.response.SuperHeroItemResponse
import com.example.proyecto_2.adapter.SuperheroViewHolder


class SuperheroAdapter (var superheroList:List<SuperHeroItemResponse> = emptyList(),
                        private val onItemSelected:(String)->Unit,
                        private val onSaveToDB: ((String, String) -> Unit)
    ): RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(superheroList:List<SuperHeroItemResponse>){
        this.superheroList=superheroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_superhero,parent,false))
    }

    override fun onBindViewHolder(viewholder: SuperheroViewHolder, position: Int) {
        viewholder.bind(superheroList[position],onItemSelected,onSaveToDB)
    }

    override fun getItemCount() = superheroList.size

}
