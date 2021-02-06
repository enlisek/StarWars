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
import com.example.starwars.viewmodel.adapters.MovieAdapter
import com.example.starwars.viewmodel.viewModels.CharacterListViewModel
import com.example.starwars.viewmodel.viewModels.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MovieList.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter1 : MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var characterListViewModel: CharacterListViewModel


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
        characterListViewModel = ViewModelProvider(requireActivity()).get(CharacterListViewModel::class.java)
        movieListViewModel = ViewModelProvider(requireActivity()).get(MovieListViewModel::class.java)
        viewManager = LinearLayoutManager(requireContext())
        movieListViewModel.updateFilms()
        adapter1 = MovieAdapter(movieListViewModel.films,mainViewModel,characterListViewModel)
        movieListViewModel.films.observe(viewLifecycleOwner, { adapter1.notifyDataSetChanged() })
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter1.notifyDataSetChanged()
        movieList.apply {
            adapter = adapter1
            layoutManager = viewManager
        }

        button_goToMainMenuFromMovieList.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_movieList2_to_mainMenu)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MovieList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}