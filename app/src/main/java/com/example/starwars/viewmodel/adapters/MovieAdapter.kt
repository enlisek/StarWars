package com.example.starwars.viewmodel.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.model.entities.Film
import com.example.starwars.viewmodel.MainViewModel

class MovieAdapter(var data: LiveData<List<Film>>,val mainViewModel: MainViewModel): RecyclerView.Adapter<MovieAdapter.NoteHolder>() {
    lateinit  var context: Context
    class NoteHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row,parent,false) as View
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        val textViewOneRow = holder.itemView.findViewById<TextView>(R.id.textViewOneRow)
        val buttonOneRow = holder.itemView.findViewById<TextView>(R.id.buttonOneRow)

        textViewOneRow.text = data.value?.get(position)?.title
        buttonOneRow.setOnClickListener {
            view-> run {
            mainViewModel.selectedFilm = data.value?.get(position)!!
            mainViewModel.getPeopleFromUrlList(mainViewModel.selectedFilm.characters)
            mainViewModel.getPlanetsFromUrlList(mainViewModel.selectedFilm.planets)
            view.findNavController().navigate(R.id.action_movieList2_to_movieInfo) }
        }
    }


    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}