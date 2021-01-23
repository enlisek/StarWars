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


class CharacterAdapter(var data: LiveData<List<Person>>,val mainViewModel: MainViewModel): RecyclerView.Adapter<CharacterAdapter.NoteHolder>() {
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
        textViewOneRow.text = data.value?.get(position)?.name
        buttonOneRow.setOnClickListener {
            view-> run {
            mainViewModel.selectedCharacter = data.value?.get(position)!!
            mainViewModel.getFilmsFromUrlList(mainViewModel.selectedCharacter.films)
            mainViewModel.setPlanetFromUrl(mainViewModel.selectedCharacter.homeworld)
            //Log.d("XX",mainViewModel.selectedCharacter.toString())
            view.findNavController().navigate(R.id.action_characterList2_to_characterInfo) }
        }

    }


    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}