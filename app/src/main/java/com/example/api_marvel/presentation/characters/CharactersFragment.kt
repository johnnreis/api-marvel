package com.example.api_marvel.presentation.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.example.api_marvel.databinding.FragmentCharactersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private var _binding : FragmentCharactersBinding? = null
    private val binding get() = _binding!!

    private lateinit var charactersAdapter : CharactersAdapter

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
        observeInitialLoadState()

        lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.charactersPagingData("").collect { pagingData ->
                    charactersAdapter.submitData(pagingData)
                }
            }
        }

    }

    private fun initCharactersAdapter() {
        charactersAdapter = CharactersAdapter()
        binding.recyclerCharacters.run {
            scrollToPosition(0)
            setHasFixedSize(true)
            adapter = charactersAdapter.withLoadStateFooter(
                footer = CharactersLoadingStateAdapter(
                    charactersAdapter::retry
                )
            )

        }
    }

    private fun observeInitialLoadState() {
        lifecycleScope.launch {
            charactersAdapter.loadStateFlow.collectLatest { loadState ->
                binding.flipperCharacters.displayedChild = when (loadState.refresh) {
                    is LoadState.Loading -> {
                        listenStateLoading(true)
                        FLIPPER_CHILD_LOADING
                    }
                    is LoadState.NotLoading -> {
                        listenStateLoading(false)
                        FLIPPER_CHILD_SUCCESS_CHARACTERS
                    }
                    is LoadState.Error -> {
                        listenStateLoading(false)
                        FLIPPER_CHILD_ERROR

                    }
                }
            }
        }
    }

    private fun listenStateLoading(isVisibility: Boolean) {
        if (isVisibility) {
            binding.fvProgressLoading.progressLoading.isVisible = true
            binding.fvProgressLoading.textTryAgain.isVisible = false
            binding.fvProgressLoading.btnRetryAgain.isVisible = false
        } else {
            binding.fvProgressLoading.textTryAgain.isVisible = true
            binding.fvProgressLoading.progressLoading.isVisible = false
            binding.fvProgressLoading.btnRetryAgain.isVisible = true
            binding.fvProgressLoading.btnRetryAgain.also {
                it.setOnClickListener {
                   TODO("tratar dados para reload dos dados")
                }
            }
        }
    }

    companion object {
        private const val FLIPPER_CHILD_LOADING = 0
        private const val FLIPPER_CHILD_SUCCESS_CHARACTERS = 1
        private const val FLIPPER_CHILD_ERROR = 2
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}