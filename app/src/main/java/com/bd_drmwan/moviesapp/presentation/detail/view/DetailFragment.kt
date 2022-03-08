package com.bd_drmwan.moviesapp.presentation.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.common_extensions.makeStatusBarTransparent
import com.bd_drmwan.common_extensions.resetStatusBarColor
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.moviesapp.R
import com.bd_drmwan.moviesapp.databinding.FragmentDetailBinding
import com.bd_drmwan.moviesapp.presentation.detail.viewmodel.DetailViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlin.math.abs

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding

    private val viewModel: DetailViewModel by viewModels()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        activity?.makeStatusBarTransparent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.resetStatusBarColor(R.color.colorPrimary)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intiListeners()
        initObservers()

        binding?.apply {
            header.imgMovie.loadImage(args.movie?.posterUri)
            header.tvTitle.text = args.movie?.title
            toolbar.tvTitle.text = args.movie?.title
        }
    }

    private fun intiListeners() {
        binding?.apply {
            toolbar.icBack.setOnClickListener { findNavController().popBackStack() }
            appBarLayout.addOnOffsetChangedListener(
                AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                    val scrollPosition = abs(verticalOffset) - appBarLayout.totalScrollRange
                    toolbar.tvTitle.isVisible = abs(scrollPosition) <= 100
                }
            )

        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            args.movie?.id?.let { movieId ->
                viewModel.getCast(movieId).collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {}
                        is Resource.Success -> {

                        }
                    }
                }
            }
        }
    }

}