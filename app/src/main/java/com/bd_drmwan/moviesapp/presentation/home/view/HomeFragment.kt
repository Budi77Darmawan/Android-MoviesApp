package com.bd_drmwan.moviesapp.presentation.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bd_drmwan.common_extensions.toast
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.databinding.FragmentHomeBinding
import com.bd_drmwan.moviesapp.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPopularMovie()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.listMovies.collect {
                when (it) {
                    is Resource.Loading -> toast("isLoading")
                    is Resource.Error -> toast("isError")
                    is Resource.Success -> binding?.tvSimple?.text = it.data?.first()?.title.toString()
                }
            }
        }
    }
}