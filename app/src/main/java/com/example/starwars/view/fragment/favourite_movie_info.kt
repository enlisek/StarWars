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
import kotlinx.android.synthetic.main.fragment_favourite_movie_info.*
import kotlinx.android.synthetic.main.fragment_favourite_planet_info.*
import kotlinx.android.synthetic.main.fragment_favourite_planet_info.buttonBackToFavouritePlanets
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [favourite_movie.newInstance] factory method to
 * create an instance of this fragment.
 */
class favourite_movie : Fragment() {
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
        favouritiesViewModel = ViewModelProvider(requireActivity()).get(FavouritiesViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_movie_info, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBox_favouriteMovieInFavouriteMovieInfo.isChecked = true

        textView_titleOfFavouriteMovie.text = favouritiesViewModel.selectedFilm.title
        textView_quotationOfFavouriteMovie.text = favouritiesViewModel.selectedFilm.opening_crawl
        textView_dateOfReleaseOfFavouriteMovie.text = "Release date: : ${favouritiesViewModel.selectedFilm.release_date}"
        textView_directionOfFavouriteMovie.text = "Director: ${favouritiesViewModel.selectedFilm.director}"
        textView_favouriteMovieProducer.text = "Producer: ${favouritiesViewModel.selectedFilm.producer}"

        buttonBackToFavouriteMovies.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_favourite_movie_to_favourite_movies)
        }

        checkBox_favouriteMovieInFavouriteMovieInfo.setOnClickListener {  view ->
            if(checkBox_favouriteMovieInFavouriteMovieInfo.isChecked)
            {
                favouritiesViewModel.add(favouritiesViewModel.selectedFilm)
            }
            else
            {
                GlobalScope.launch{
                    favouritiesViewModel.delete(favouritiesViewModel.selectedFilm)
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
         * @return A new instance of fragment favourite_movie.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            favourite_movie().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}