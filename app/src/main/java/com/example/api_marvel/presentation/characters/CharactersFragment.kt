package com.example.api_marvel.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.api_marvel.databinding.FragmentCharactersBinding
import com.example.core.domain.model.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding : FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val charactersAdapter = CharactersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCharactersAdapter()

        charactersAdapter.submitList(
            listOf(
                Character("3D-Man", "https://cinepop.com.br/wp-content/uploads/" +
                        "2019/03/capitamarvel_27-1280x720-696x392.jpg"),
                Character("3D-Man", "https://cinepop.com.br/wp-content/uploads/" +
                        "2019/03/capitamarvel_27-1280x720-696x392.jpg"),
                Character("3D-Man", "https://cinepop.com.br/wp-content/uploads/" +
                        "2019/03/capitamarvel_27-1280x720-696x392.jpg"),
                Character("3D-Man", "https://cinepop.com.br/wp-content/uploads/" +
                        "2019/03/capitamarvel_27-1280x720-696x392.jpg"),
                Character("3D-Man", "https://cinepop.com.br/wp-content/uploads/" +
                        "2019/03/capitamarvel_27-1280x720-696x392.jpg"),
                Character("3D-Man", "https://cinepop.com.br/wp-content/uploads/" +
                        "2019/03/capitamarvel_27-1280x720-696x392.jpg"),
            )
        )

    }

    private fun initCharactersAdapter() {
        binding.recyclerCharacters.run {
            setHasFixedSize(true)
            adapter = charactersAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}