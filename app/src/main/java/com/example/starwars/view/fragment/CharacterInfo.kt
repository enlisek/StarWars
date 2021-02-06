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
import kotlinx.android.synthetic.main.fragment_main_menu.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CharacterInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class CharacterInfo : Fragment() {
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

        return inflater.inflate(R.layout.fragment_character_info, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textView_characterName.text = mainViewModel.selectedCharacter.name
        textView_characterHeight.text = "Height: ${mainViewModel.selectedCharacter.height}"
        textview_characterWeight.text = "Weight: ${mainViewModel.selectedCharacter.mass}"
        textview_dateOfBirth.text = "Birth year: ${mainViewModel.selectedCharacter.birth_year}"
        textview_hairColor.text = "Hair color: ${mainViewModel.selectedCharacter.hair_color}"
        textview_skinColor.text = "Skin color: ${mainViewModel.selectedCharacter.skin_color}"
        textview_genderOfCharacter.text = "Gender: ${mainViewModel.selectedCharacter.gender}"
        textview_eyeColor.text = "Eye color: ${mainViewModel.selectedCharacter.eye_color}"
        textView_character_homeworld.text = "Homeworld: ${mainViewModel.selectedCharacter.homeworld}"


        button_goToCharacterListFromCharacterInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_characterInfo_to_characterList2)
        }
         button_goToMainMenuFromCharacterInfo.setOnClickListener {
                view->view.findNavController().navigate(R.id.action_characterInfo_to_mainMenu)
        }
        checkBox_favouriteCharacter.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
            {
                Log.d("XX","checked")
                //dodaj do lokalnej bazy danych
            }
            else
            {
                Log.d("XX","unchecked")
                //usun z lokalnej bazy

            }}

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CharacterInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CharacterInfo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}