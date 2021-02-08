package com.example.starwars.viewmodel.adapters

import com.example.starwars.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.model.LocalDataBase.entities.FavFilm
import com.example.starwars.viewmodel.viewModels.FavouritiesViewModel

class LocalFilmAdapter(var data: LiveData<List<FavFilm>>, val favouritiesViewModel: FavouritiesViewModel): RecyclerView.Adapter<LocalFilmAdapter.NoteHolder>() {

    lateinit  var context: Context
    class NoteHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row,parent,false) as View
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val textViewOneRow = holder.itemView.findViewById<TextView>(R.id.textViewOneRow)
        textViewOneRow.text = data.value?.get(position)?.title
        textViewOneRow.setOnClickListener {
            view-> run {

            favouritiesViewModel.selectedFilm = data.value?.get(position)!!

            view.findNavController().navigate(R.id.action_favourite_movies_to_favourite_movie) }
        }

    }
    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}
