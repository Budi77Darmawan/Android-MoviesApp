package com.bd_drmwan.moviesapp.presentation.splash.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.R
import com.bd_drmwan.moviesapp.databinding.FragmentSplashBinding
import com.bd_drmwan.moviesapp.presentation.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding

    private val viewModel: SplashViewModel by viewModels()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.genresMovies().collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                    is Resource.Success -> {
                        delay(1000)
                        findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    }
                }
            }
        }
    }

}