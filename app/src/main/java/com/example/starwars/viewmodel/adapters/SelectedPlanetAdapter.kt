package com.example.starwars.viewmodel.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.model.entities.Planet
import com.example.starwars.viewmodel.MainViewModel

class SelectedPlanetAdapter(var data: LiveData<List<Planet>>,val mainViewModel: MainViewModel): RecyclerView.Adapter<SelectedPlanetAdapter.NoteHolder>() {
    class NoteHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row_no_click,parent,false) as View
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        val textViewOneRow = holder.itemView.findViewById<TextView>(R.id.textView6)

        textViewOneRow.text = data.value?.get(position)?.name
    }


    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}