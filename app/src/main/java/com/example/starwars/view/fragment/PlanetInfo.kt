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
import com.example.starwars.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_character_info.*
import kotlinx.android.synthetic.main.fragment_movie_info.*
import kotlinx.android.synthetic.main.fragment_planet_info.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlanetInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanetInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mainViewModel: MainViewModel

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
        mainViewModel =  ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.fragment_planet_info, container, false)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textview_nameOfThePlanet.text = mainViewModel.selectedPlanet.name
        textview_rotation.text = "Rotation period: ${mainViewModel.selectedPlanet.rotation_period}"
        textview_orbital.text = "Orbital period: ${mainViewModel.selectedPlanet.orbital_period}"
        textview_diameter.text = "Diameter: ${mainViewModel.selectedPlanet.diameter}"
        textview_population.text = "Population: ${mainViewModel.selectedPlanet.population}"
        textview_climate.text = "Climate: ${mainViewModel.selectedPlanet.climate}"
        textview_terrain.text = "Terrain: ${mainViewModel.selectedPlanet.terrain}"

        mainViewModel.getPeopleFromUrlList(mainViewModel.selectedPlanet.residents)
        Log.d("XYZ",mainViewModel.listOfPeople.toString())
        button_goToCharacterListFromPlanetInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_planetInfo_to_characterList2)
        }
        button_goToMovieListFromPlanetInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_planetInfo_to_movieList2)
        }
        button_goToPlanetListFormPlanetInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_planetInfo_to_planetList2)
        }
        button_goToMainMenuFromPlanetInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_planetInfo_to_mainMenu)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlanetInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlanetInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}