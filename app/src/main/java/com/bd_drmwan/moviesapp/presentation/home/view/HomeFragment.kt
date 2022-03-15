package com.bd_drmwan.moviesapp.presentation.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bd_drmwan.common_extensions.*
import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.R
import com.bd_drmwan.moviesapp.databinding.ContainerItemGridMoviesBinding
import com.bd_drmwan.moviesapp.databinding.FragmentHomeBinding
import com.bd_drmwan.moviesapp.presentation.home.adapter.BannerAdapter
import com.bd_drmwan.moviesapp.presentation.home.adapter.GridActorsAdapter
import com.bd_drmwan.moviesapp.presentation.home.adapter.MoviesAdapter
import com.bd_drmwan.moviesapp.presentation.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()
    private val bannerAdapter by lazy { BannerAdapter() }

    override fun onDestroy() {
        super.onDestroy()
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

        initToolbar()
        initObservers()
    }

    private fun initToolbar() {
        binding?.toolbar?.apply {
            tvTitle.text = getString(R.string.discover)
            icBack.gone()
            icSearchToolbar.visible()
            icSearchToolbar.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
            icFavoriteToolbar.visible()
            icFavoriteToolbar.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_listFavoriteFragment)
            }
        }
    }

    private fun setupBanner(movies: List<MovieModel>?) {
        val numOfBanners = 5
        binding?.banner?.carouselBanner?.apply {
            adapter = bannerAdapter
            start(5, TimeUnit.SECONDS)

        }
        bannerAdapter.setData(movies?.take(numOfBanners))
    }

    private fun setupRecyclerViewMovies(
        container: ContainerItemGridMoviesBinding?,
        moviesType: MoviesType,
        movies: List<MovieModel>?
    ) {
        val gridAdapter = MoviesAdapter(true)
        gridAdapter.submitList(movies?.take(7)?.toMutableList())
        gridAdapter.onRootClicked {
            val toDetail = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(toDetail)
        }

        container?.apply {
            viewGroup.visible()
            tvTypeMovies.text = moviesType.value
            rvMovies.adapter = gridAdapter
            rvMovies.layoutManager = horizontalLinearLayoutManager()

            btnSeeAll.setOnClickListener {
                val toList =
                    HomeFragmentDirections.actionHomeFragmentToListMoviesFragment(moviesType)
                findNavController().navigate(toList)
            }
        }
    }

    private fun setupRecyclerViewActors(casts: List<CastModel>?) {
        val gridAdapter = GridActorsAdapter()
        gridAdapter.setData(casts?.take(7))
        gridAdapter.onRootClicked {
            toast(it?.name.toString())
        }

        binding?.layoutPopularActors?.apply {
            btnSeeAll.gone()
            viewGroup.visible()
            rvActors.adapter = gridAdapter
            rvActors.layoutManager = horizontalLinearLayoutManager()
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.upComingMovies.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding?.banner?.shimmer?.root?.visible()
                    }
                    is Resource.Error -> {
                        binding?.banner?.shimmer?.root?.gone()
                    }
                    is Resource.Success -> {
                        binding?.banner?.shimmer?.root?.gone()
                        setupBanner(it.data)
                    }
                    else -> Unit
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.nowPlayingMovies.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding?.layoutPopularMovies?.shimmer?.root?.visible()
                    }
                    is Resource.Error -> {
                        binding?.layoutPopularMovies?.shimmer?.root?.gone()
                    }
                    is Resource.Success -> {
                        binding?.layoutPopularMovies?.shimmer?.root?.gone()
                        setupRecyclerViewMovies(
                            binding?.layoutPopularMovies,
                            MoviesType.NOW_PLAYING,
                            it.data
                        )
                    }
                    else -> Unit
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.topRatedMovies.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding?.layoutTopRatedMovies?.shimmer?.root?.visible()
                    }
                    is Resource.Error -> {
                        binding?.layoutTopRatedMovies?.shimmer?.root?.gone()
                    }
                    is Resource.Success -> {
                        binding?.layoutTopRatedMovies?.shimmer?.root?.gone()
                        setupRecyclerViewMovies(
                            binding?.layoutTopRatedMovies,
                            MoviesType.TOP_RATED,
                            it.data
                        )
                    }
                    else -> Unit
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            viewModel.popularActors.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding?.layoutPopularActors?.shimmer?.root?.visible()
                    }
                    is Resource.Error -> {
                        binding?.layoutPopularActors?.shimmer?.root?.gone()
                    }
                    is Resource.Success -> {
                        binding?.layoutPopularActors?.shimmer?.root?.gone()
                        setupRecyclerViewActors(it.data)
                    }
                    else -> Unit
                }
            }
        }
    }
}