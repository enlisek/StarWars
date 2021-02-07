package com.example.starwars.viewmodel.adapters

import com.example.starwars.R
import com.example.starwars.model.LocalDataBase.entities.FavPerson
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.viewmodel.viewModels.FavouritiesViewModel

class LocalCharacterAdapter(var data: LiveData<List<FavPerson>>, val favouritiesViewModel: FavouritiesViewModel): RecyclerView.Adapter<LocalCharacterAdapter.NoteHolder>() {

    lateinit  var context: Context
    class NoteHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row,parent,false) as View
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val textViewOneRow = holder.itemView.findViewById<TextView>(R.id.textViewOneRow)
        textViewOneRow.text = data.value?.get(position)?.name
        textViewOneRow.setOnClickListener {
            view-> run {
            favouritiesViewModel.selectedCharacter = data.value?.get(position)!!
            view.findNavController().navigate(R.id.action_favourite_characters_to_favourite_character_info) }
        }

    }


    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}
