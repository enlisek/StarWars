package com.example.starwars.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
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
import com.example.starwars.viewmodel.adapters.SelectedCharacterAdapter
import com.example.starwars.viewmodel.adapters.SelectedMovieAdapter
import com.example.starwars.viewmodel.adapters.SelectedPlanetAdapter
import com.example.starwars.viewmodel.viewModels.MovieInfoViewModel
import com.example.starwars.viewmodel.viewModels.PlanetInfoViewModel
import kotlinx.android.synthetic.main.fragment_character_info.*
import kotlinx.android.synthetic.main.fragment_movie_info.*
import kotlinx.android.synthetic.main.fragment_planet_info.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieInfo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var mainViewModel: MainViewModel
    private lateinit var movieInfoViewModel: MovieInfoViewModel

    private lateinit var adapter1 : SelectedPlanetAdapter
    private lateinit var adapter2 : SelectedCharacterAdapter
    private lateinit var viewManager1: RecyclerView.LayoutManager
    private lateinit var viewManager2: RecyclerView.LayoutManager

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

        mainViewModel =  ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        movieInfoViewModel = ViewModelProvider(requireActivity()).get(MovieInfoViewModel::class.java)

        viewManager1 = LinearLayoutManager(requireContext())
        viewManager2 = LinearLayoutManager(requireContext())

        adapter1 = SelectedPlanetAdapter(movieInfoViewModel.planets,mainViewModel)
        adapter2 = SelectedCharacterAdapter(movieInfoViewModel.characters,mainViewModel)

        movieInfoViewModel.planets.observe(viewLifecycleOwner, { adapter1.notifyDataSetChanged() })
        movieInfoViewModel.characters.observe(viewLifecycleOwner, { adapter2.notifyDataSetChanged() })
        movieInfoViewModel.isF.observe(viewLifecycleOwner) { checkBox_favouriteMovie.isChecked = movieInfoViewModel.isF.value?:false  }



        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter1.notifyDataSetChanged()
        recyclerView_movie_planets.apply {
            adapter = adapter1
            layoutManager = viewManager1
        }
        adapter2.notifyDataSetChanged()
        recyclerView_movie_characters.apply {
            adapter = adapter2
            layoutManager = viewManager2
        }

        textview_quotation.movementMethod= ScrollingMovementMethod()

        textview_titleOfMovie.text = mainViewModel.selectedFilm.title
        textview_quotation.text = mainViewModel.selectedFilm.opening_crawl
        textview_dateOfRelease.text = "Release date: ${mainViewModel.selectedFilm.release_date}"
        textview_direction.text = "Director: ${mainViewModel.selectedFilm.director}"
        textview_movieProducer.text = "Producer: ${mainViewModel.selectedFilm.producer}"
        textview_idMovie.text = mainViewModel.selectedFilm.episode_id.toString()

        button_goToMovieListFromMovieInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_movieInfo_to_movieList2)
        }

        button_goToMainMenuFromMovieInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_movieInfo_to_mainMenu)
        }

        checkBox_favouriteMovie.setOnClickListener {  view ->
            if(checkBox_favouriteMovie.isChecked)
            {
                Log.d("XX","checked")
                movieInfoViewModel.add(mainViewModel.selectedFilm)
            }
            else
            {
                Log.d("XX","unchecked")
                GlobalScope.launch{
                    movieInfoViewModel.remove(mainViewModel.selectedFilm)
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
         * @return A new instance of fragment MovieInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}