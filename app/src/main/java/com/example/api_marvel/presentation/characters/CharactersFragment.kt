package com.example.api_marvel.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.api_marvel.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding : FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private val charactersAdapter = CharactersAdapter()

    private val viewModel : CharactersViewModel by viewModels()

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

        lifecycleScope.launch {
            viewModel.charactersPagingData("").collect { pagingData ->
                charactersAdapter.submitData(pagingData)
            }
        }

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