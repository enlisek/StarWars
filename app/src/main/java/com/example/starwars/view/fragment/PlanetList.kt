package com.example.starwars.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.viewmodel.MainViewModel
import com.example.starwars.viewmodel.adapters.CharacterAdapter
import com.example.starwars.viewmodel.adapters.PlanetAdapter
import com.example.starwars.viewmodel.viewModels.CharacterListViewModel
import com.example.starwars.viewmodel.viewModels.PlanetInfoViewModel
import com.example.starwars.viewmodel.viewModels.PlanetListViewModel
import kotlinx.android.synthetic.main.fragment_character_info.*
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.fragment_planet_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlanetList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlanetList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter1 : PlanetAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var planetListViewModel: PlanetListViewModel
    private lateinit var planetInfoViewModel: PlanetInfoViewModel

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
        planetListViewModel = ViewModelProvider(requireActivity()).get(PlanetListViewModel::class.java)
        planetInfoViewModel = ViewModelProvider(requireActivity()).get(PlanetInfoViewModel::class.java)

        viewManager = LinearLayoutManager(requireContext())
        planetListViewModel.updatePlanets()
        adapter1 = PlanetAdapter(planetListViewModel.planets,mainViewModel,planetInfoViewModel)
        planetListViewModel.planets.observe(viewLifecycleOwner, { adapter1.notifyDataSetChanged() })
        return inflater.inflate(R.layout.fragment_planet_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter1.notifyDataSetChanged()
        planetList.apply {
            adapter = adapter1
            layoutManager = viewManager
        }
        button_goToMainMenuFromPlanetList.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_planetList2_to_mainMenu)
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlanetList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlanetList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}