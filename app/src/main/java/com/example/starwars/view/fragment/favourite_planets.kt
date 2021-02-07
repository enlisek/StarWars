package com.example.starwars.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.viewmodel.adapters.LocalCharacterAdapter
import com.example.starwars.viewmodel.adapters.LocalPlanetAdapter
import com.example.starwars.viewmodel.viewModels.FavouritiesViewModel
import kotlinx.android.synthetic.main.fragment_favourite_characters.*
import kotlinx.android.synthetic.main.fragment_favourite_movies.*
import kotlinx.android.synthetic.main.fragment_favourite_planets.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [favourite_planets.newInstance] factory method to
 * create an instance of this fragment.
 */
class favourite_planets : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter1: LocalPlanetAdapter
    private lateinit var viewManager1: RecyclerView.LayoutManager
    private lateinit var favouritiesViewModel: FavouritiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        favouritiesViewModel= ViewModelProvider(requireActivity()).get(FavouritiesViewModel::class.java)
        viewManager1= LinearLayoutManager(requireContext())

        adapter1= LocalPlanetAdapter(favouritiesViewModel.listOfPlanets,favouritiesViewModel)
        favouritiesViewModel.listOfPlanets.observe(viewLifecycleOwner, Observer {
            adapter1.notifyDataSetChanged()})
        return inflater.inflate(R.layout.fragment_favourite_planets, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_favouritePlanets.apply {
            adapter=adapter1
            layoutManager=viewManager1
        }
        buttonBackFromPlanetsToFavourites.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_favourite_planets_to_favourites)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment favourite_planets.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                favourite_planets().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}