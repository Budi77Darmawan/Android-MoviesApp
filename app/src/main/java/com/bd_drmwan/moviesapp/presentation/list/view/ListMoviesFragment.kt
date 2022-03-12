package com.bd_drmwan.moviesapp.presentation.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bd_drmwan.common_extensions.verticalLinearLayoutManager
import com.bd_drmwan.common_extensions.visible
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.databinding.FragmentListMoviesBinding
import com.bd_drmwan.moviesapp.presentation.adapter.ListMovieAdapter
import com.bd_drmwan.moviesapp.presentation.list.viewmodel.ListMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ListMoviesFragment : Fragment() {
    private var _binding: FragmentListMoviesBinding? = null
    private val binding get() = _binding

    private val viewModel: ListMoviesViewModel by viewModels()
    private val args by navArgs<ListMoviesFragmentArgs>()
    private val adapterMovie by lazy { ListMovieAdapter() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.apply {
            tvTitle.text = args.movieType.value
            icBack.visible()
            icBack.setOnClickListener { findNavController().popBackStack() }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getMovies(args.movieType).collect {
                when (it) {
                    is Resource.Success -> {
                        binding?.rvMovies?.apply {
                            adapter = adapterMovie
                            layoutManager = verticalLinearLayoutManager()
                        }
                        adapterMovie.setData(it.data)
                        adapterMovie.onRootClicked { movie ->
                            val toDetail =
                                ListMoviesFragmentDirections.actionListMoviesFragmentToDetailFragment(
                                    movie
                                )
                            findNavController().navigate(toDetail)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }
}