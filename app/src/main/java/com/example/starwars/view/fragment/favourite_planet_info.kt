package com.example.starwars.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.starwars.R
import com.example.starwars.viewmodel.viewModels.FavouritiesViewModel
import kotlinx.android.synthetic.main.fragment_favourite_character_info.*
import kotlinx.android.synthetic.main.fragment_favourite_planet_info.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [favourite_planet_info.newInstance] factory method to
 * create an instance of this fragment.
 */
class favourite_planet_info : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var favouritiesViewModel: FavouritiesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        favouritiesViewModel = ViewModelProvider(requireActivity()).get(FavouritiesViewModel::class.java)

        return inflater.inflate(R.layout.fragment_favourite_planet_info, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkBox_favouritePlanetInFavouritePlanetInfo.isChecked = true

        textView_nameOfFavouritePlanet.text = favouritiesViewModel.selectedPlanet.name
        textView_rotationOfFavouritePlanet.text = "Rotation period: ${favouritiesViewModel.selectedPlanet.rotation_period}"
        textView_orbitalOfFavouritePlanet.text = "Orbital period: ${favouritiesViewModel.selectedPlanet.orbital_period}"
        textView_diameterOfFavouritePlanet.text = "Diameter: ${favouritiesViewModel.selectedPlanet.diameter}"
        textView_populationOfFavouritePlanet.text = "Population: ${favouritiesViewModel.selectedPlanet.population}"
        textView_climateOfFavouritePlanet.text = "Climate: ${favouritiesViewModel.selectedPlanet.climate}"
        textView_terrainOfFavouritePlanet.text = "Terrain: ${favouritiesViewModel.selectedPlanet.terrain}"

        buttonBackToFavouritePlanets.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_favourite_planet_info_to_favourite_planets)
        }

        checkBox_favouritePlanetInFavouritePlanetInfo.setOnClickListener {  view ->
            if(checkBox_favouritePlanetInFavouritePlanetInfo.isChecked)
            {
                Log.d("XX","checked")
                favouritiesViewModel.add(favouritiesViewModel.selectedPlanet)
            }
            else
            {
                Log.d("XX","unchecked")
                GlobalScope.launch{
                    favouritiesViewModel.delete(favouritiesViewModel.selectedPlanet)
                }


            }
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment favourite_planet_info.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            favourite_planet_info().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}