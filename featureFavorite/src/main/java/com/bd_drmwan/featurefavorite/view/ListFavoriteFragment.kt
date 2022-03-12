package com.bd_drmwan.featurefavorite.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bd_drmwan.common_extensions.gone
import com.bd_drmwan.common_extensions.verticalLinearLayoutManager
import com.bd_drmwan.common_extensions.visible
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.featurefavorite.R
import com.bd_drmwan.featurefavorite.databinding.FragmentListFavoriteBinding
import com.bd_drmwan.featurefavorite.di.DaggerFavoriteComponent
import com.bd_drmwan.featurefavorite.viewmodel.FavoriteViewModel
import com.bd_drmwan.featurefavorite.viewmodel.ViewModelFactory
import com.bd_drmwan.moviesapp.modules.FavoriteModuleDependencies
import com.bd_drmwan.moviesapp.presentation.adapter.ListMovieAdapter
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ListFavoriteFragment : Fragment() {
    private var _binding: FragmentListFavoriteBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavoriteViewModel by viewModels { factory }
    private val adapterMovie by lazy { ListMovieAdapter() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        setupRecyclerMovies()

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.getFavoriteMovies().collect {
                when (it) {
                    is Resource.Success -> {
                        if (it.data?.isNullOrEmpty() == true) {
                            binding?.animEmpty?.playAnimation()
                            binding?.animEmpty?.visible()
                            binding?.rvMovies?.gone()
                        } else {
                            binding?.animEmpty?.pauseAnimation()
                            binding?.animEmpty?.gone()
                            binding?.rvMovies?.visible()
                            adapterMovie.setData(it.data)
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupRecyclerMovies() {
        binding?.rvMovies?.apply {
            adapter = adapterMovie
            layoutManager = verticalLinearLayoutManager()
        }
        adapterMovie.onRootClicked {
            val toDetail =
                ListFavoriteFragmentDirections.actionListFavoriteFragmentToDetailFragment(it, true)
            findNavController().navigate(toDetail)
        }
    }

    private fun initToolbar() {
        binding?.toolbar?.apply {
            tvTitle.text = getString(R.string.favorite)
            icBack.visible()
            icBack.setOnClickListener { findNavController().popBackStack() }
        }
    }

}