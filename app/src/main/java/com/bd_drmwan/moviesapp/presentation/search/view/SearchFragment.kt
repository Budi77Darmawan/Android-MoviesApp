package com.bd_drmwan.moviesapp.presentation.search.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bd_drmwan.common_extensions.*
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.core.utils.DebouncerTextListener
import com.bd_drmwan.moviesapp.R
import com.bd_drmwan.moviesapp.databinding.FragmentSearchBinding
import com.bd_drmwan.moviesapp.presentation.home.adapter.MoviesAdapter
import com.bd_drmwan.moviesapp.presentation.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import org.jetbrains.anko.sdk27.coroutines.onEditorAction

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding

    private val viewModel: SearchViewModel by viewModels()
    private val moviesAdapter by lazy { MoviesAdapter() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding?.searchBar?.apply {
            btnBack.setOnClickListener { findNavController().popBackStack() }
            iconCancelSearch.setOnClickListener {
                inputSearch.text?.clear()
            }
            inputSearch.onEditorAction { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    inputSearch.clearFocus()
                }
            }
            inputSearch.doAfterTextChanged {
                iconCancelSearch.isVisible = it.toString().isNotEmpty()
            }
            inputSearch.addTextChangedListener(
                DebouncerTextListener(viewLifecycleOwner.lifecycle) {
                    it?.let { query ->
                        if (query.trim().isNotEmpty()) {
                            viewModel.searchMovies(query)
                        }
                    }
                })
        }
    }

    private fun initRecyclerView() {
        binding?.rvMovies?.apply {
            adapter = moviesAdapter
            layoutManager = verticalGridLayoutManager(2)
        }
        moviesAdapter.onRootClicked {
            val toDetail = SearchFragmentDirections.actionSearchFragmentToDetailFragment(it)
            findNavController().navigate(toDetail)
        }
    }

    private fun showSearchLoading() {
        binding?.apply {
            rvMovies.gone()
            searchAnimation.setAnimation(R.raw.search_anim)
            searchAnimation.visible()
            searchAnimation.playAnimation()
        }
    }

    private fun showNotFoundMovies() {
        binding?.apply {
            rvMovies.gone()
            searchAnimation.visible()
            searchAnimation.setAnimation(R.raw.empty_box)
            searchAnimation.playAnimation()
        }
    }

    private fun initObservers() {
        binding?.apply {
            viewLifecycleOwner.lifecycleScope.launchWhenResumed {
                viewModel.moviesByQuery.collect {
                    when (it) {
                        is Resource.Loading -> {
                            showSearchLoading()
                        }
                        is Resource.Error -> {
                            showNotFoundMovies()
                        }
                        is Resource.Success -> {
                            if (it.data.isNullOrEmpty()) {
                                showNotFoundMovies()
                            } else {
                                rvMovies.visible()
                                searchAnimation.gone()
                                searchAnimation.pauseAnimation()
                                moviesAdapter.setData(it.data)
                            }
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

}