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
import com.example.starwars.viewmodel.adapters.CharacterAdapter
import com.example.starwars.viewmodel.viewModels.CharacterListViewModel
import com.example.starwars.viewmodel.MainViewModel
import com.example.starwars.viewmodel.viewModels.CharacterInfoViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterList.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterList : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter1 : CharacterAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var characterListViewModel: CharacterListViewModel
    private lateinit var characterInfoViewModel: CharacterInfoViewModel
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
        mainViewModel =  ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        characterListViewModel = ViewModelProvider(requireActivity()).get(CharacterListViewModel::class.java)
        characterInfoViewModel = ViewModelProvider(requireActivity()).get(CharacterInfoViewModel::class.java)

        viewManager = LinearLayoutManager(requireContext())
        adapter1 = CharacterAdapter(characterListViewModel.characters,mainViewModel,characterInfoViewModel)

        characterListViewModel.characters.observe(viewLifecycleOwner, { adapter1.notifyDataSetChanged() })

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter1.notifyDataSetChanged()
        characterList.apply {
            adapter = adapter1
            layoutManager = viewManager
        }
        button_goToMainMenuFromCharacterList.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_characterList2_to_mainMenu)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterList.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterList().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}