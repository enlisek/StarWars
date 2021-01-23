package com.example.starwars.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.starwars.R
import kotlinx.android.synthetic.main.fragment_character_info.*
import kotlinx.android.synthetic.main.fragment_favourites.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Favourites.newInstance] factory method to
 * create an instance of this fragment.
 */
class Favourites : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_goToFavouriteMovies.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_favourites_to_movieList2)
        }
        button_goToFavouriteCharacter.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_favourites_to_characterList2)
        }
        button_goToFavouritePlanet.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_favourites_to_planetList2)
        }
        button_goToMainMenuFromFavourites.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_favourites_to_mainMenu)
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Favourites.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Favourites().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}