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
import com.example.starwars.viewmodel.viewModels.PlanetInfoViewModel

class PlanetAdapter(var data: LiveData<List<Planet>>,val mainViewModel: MainViewModel, val planetInfoViewModel: PlanetInfoViewModel): RecyclerView.Adapter<PlanetAdapter.NoteHolder>() {
    class NoteHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view= LayoutInflater.from(parent.context).
        inflate(R.layout.one_row,parent,false) as View
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {

        val textViewOneRow = holder.itemView.findViewById<TextView>(R.id.textViewOneRow)
        //val buttonOneRow = holder.itemView.findViewById<TextView>(R.id.textViewOneRow)

        textViewOneRow.text = data.value?.get(position)?.name

        textViewOneRow.setOnClickListener {
                view-> run {
            mainViewModel.selectedPlanet = data.value?.get(position)!!
            planetInfoViewModel.getFilmsFromUrlList(mainViewModel.selectedPlanet.films)
            planetInfoViewModel.getPeopleFromUrlList(mainViewModel.selectedPlanet.residents)
            planetInfoViewModel.isFavourite(mainViewModel.selectedPlanet.name)
            view.findNavController().navigate(R.id.action_planetList2_to_planetInfo) }
        }
    }


    override fun getItemCount(): Int {
        return data.value?.size?:0
    }
}