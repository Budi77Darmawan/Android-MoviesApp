package com.bd_drmwan.moviesapp.presentation.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bd_drmwan.common_extensions.horizontalLinearLayoutManager
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.core.utils.setSafeOnClickListener
import com.bd_drmwan.moviesapp.R
import com.bd_drmwan.moviesapp.databinding.FragmentDetailBinding
import com.bd_drmwan.moviesapp.presentation.detail.adapter.CastGridAdapter
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

    private val castAdapter by lazy { CastGridAdapter() }
    private var isFavorite = false
    private var toast: Toast? = null
    private var showToast = false

    override fun onDestroyView() {
        super.onDestroyView()
        toast?.cancel()
        _binding = null
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

        setupView()
        intiListeners()
        initObserverCast()
        if (!isFavorite) initObserverLocalMovie()
    }

    private fun setupView() {
        binding?.apply {
            header.imgMovie.loadImage(args.movie?.posterUri)
            header.tvTitle.text = args.movie?.title
            val dateOfYear = args.movie?.releaseDate?.take(4)
            val genre = args.movie?.genre?.map { it?.name }?.joinToString(", ").toString()
            val sub = "$dateOfYear - $genre - ${args.movie?.voteAverage}"
            header.tvDesc.text = sub
            toolbar.tvTitle.text = args.movie?.title
            content.tvContent.text = args.movie?.overview
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
            btnFavorite.setSafeOnClickListener(lifecycle) {
                args.movie?.let { movie ->
                    viewModel.updateStatusMovie(isFavorite, movie)
                    isFavorite = !isFavorite
                    updateButtonFavorite()
                }
            }
        }
    }

    private fun updateButtonFavorite() {
        val message: String
        val drawable: Int
        if (isFavorite) {
            message = getString(R.string.success_add_to_favorite)
            drawable = R.drawable.ic_favorite
        } else {
            message = getString(R.string.remove_from_favorite)
            drawable = R.drawable.ic_unfavorite
        }
        binding?.btnFavorite?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                drawable
            )
        )

        if (showToast) {
            toast?.cancel()
            toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            toast?.show()
        }
        showToast = true
    }

    private fun setRecyclerCast(data: List<CastModel>?) {
        binding?.content?.rvCast?.apply {
            adapter = castAdapter
            layoutManager = horizontalLinearLayoutManager()
        }
        castAdapter.setData(data)
    }

    private fun initObserverCast() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            args.movie?.id?.let { movieId ->
                viewModel.getCast(movieId).collect {
                    when (it) {
                        is Resource.Loading -> {
                        }
                        is Resource.Error -> {
                        }
                        is Resource.Success -> {
                            setRecyclerCast(it.data)
                        }
                    }
                }
            }
        }
    }

    private fun initObserverLocalMovie() {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            args.movie?.id?.let { movieId ->
                viewModel.getLocaleMovie(movieId).collect {
                    when (it) {
                        is Resource.Loading -> {
                        }
                        is Resource.Error -> {
                        }
                        is Resource.Success -> {
                            it.data?.let { isFavorite = true } ?: run { isFavorite = false }
                            updateButtonFavorite()
                        }
                    }
                }
            }
        }
    }

}