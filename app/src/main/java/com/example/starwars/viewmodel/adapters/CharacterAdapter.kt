package com.example.starwars.viewmodel.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.model.entities.Person
import com.example.starwars.viewmodel.MainViewModel
import com.example.starwars.viewmodel.viewModels.CharacterInfoViewModel


class CharacterAdapter(var data: LiveData<List<Person>>,val mainViewModel: MainViewModel, val characterInfoViewModel: CharacterInfoViewModel): RecyclerView.Adapter<CharacterAdapter.NoteHolder>() {
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
            mainViewModel.selectedCharacter = data.value?.get(position)!!

            characterInfoViewModel.getFilmsFromUrlList(mainViewModel.selectedCharacter.films)
            characterInfoViewModel.setPlanetFromUrl(mainViewModel.selectedCharacter.homeworld)
            characterInfoViewModel.isFavourite(mainViewModel.selectedCharacter.name)

            view.findNavController().navigate(R.id.action_characterList2_to_characterInfo) }
        }

    }


    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}