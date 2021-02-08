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
import com.example.starwars.viewmodel.viewModels.CharacterInfoViewModel
import com.example.starwars.viewmodel.viewModels.FavouritiesViewModel
import kotlinx.android.synthetic.main.fragment_character_info.*
import kotlinx.android.synthetic.main.fragment_character_info.textView_characterName
import kotlinx.android.synthetic.main.fragment_favourite_character_info.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [favourite_characters.newInstance] factory method to
 * create an instance of this fragment.
 */
class favourite_character_info : Fragment() {
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
        return inflater.inflate(R.layout.fragment_favourite_character_info, container, false)
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkBox_favourtieCharacteInFavouriteCharacterInfo.isChecked = true

        textView_nameOfFavouriteCharacter.text = favouritiesViewModel.selectedCharacter.name
        textview_heightOfFavouriteCharacter.text = "Height: ${favouritiesViewModel.selectedCharacter.height}"
        textView_WeightOfFavouriteCharacter.text = "Weight: ${favouritiesViewModel.selectedCharacter.mass}"
        textView_dateOfBirthOfFavouriteCharacter.text = "Birth year: ${favouritiesViewModel.selectedCharacter.birth_year}"
        textView_hairColorOfFavouriteCharacter.text = "Hair color: ${favouritiesViewModel.selectedCharacter.hair_color}"
        textView_skinColorOfFavouriteCharacter.text = "Skin color: ${favouritiesViewModel.selectedCharacter.skin_color}"
        textView_genderOfFavouriteCharacter.text = "Gender: ${favouritiesViewModel.selectedCharacter.gender}"
        textView_eyeColorOfFavouriteCharacter.text = "Eye color: ${favouritiesViewModel.selectedCharacter.eye_color}"

        buttonBackToFavouriteCharacters.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_favourite_character_info_to_favourite_characters)
        }

        checkBox_favourtieCharacteInFavouriteCharacterInfo.setOnClickListener {  view ->
            if(checkBox_favourtieCharacteInFavouriteCharacterInfo.isChecked)
            {
                Log.d("XX","checked")
                favouritiesViewModel.add(favouritiesViewModel.selectedCharacter)
            }
            else
            {
                Log.d("XX","unchecked")
                GlobalScope.launch{
                    favouritiesViewModel.delete(favouritiesViewModel.selectedCharacter)
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
         * @return A new instance of fragment favourite_characters.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            favourite_characters().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}