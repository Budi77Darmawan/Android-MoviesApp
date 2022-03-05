package com.bd_drmwan.moviesapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.core.base.BaseAdapter
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.moviesapp.R
import com.bd_drmwan.moviesapp.databinding.ItemGridHorizontalMovieBinding

class MoviesAdapter(
    private val isLinearLayout: Boolean = false
) : BaseAdapter<MovieModel, MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemGridHorizontalMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callbackRoot?.invoke(mListData.getOrNull(bindingAdapterPosition))
            }
        }

        fun setupView(movie: MovieModel?) {
            with(binding) {
                if (isLinearLayout) {
                    setupHorizontalView(bindingAdapterPosition, root, root.context)
                }

                tvTitleMovie.text = movie?.title ?: "-"
                tvRating.text = movie?.vote?.toDouble()?.toString()
                val rate = ((movie?.vote?.toDouble() ?: 0.0) * 10).toInt()
                circularRatingMovie.progress = rate
                val colorCircular = when {
                    rate >= 75 -> ContextCompat.getColor(root.context, R.color.green)
                    rate >= 60 -> ContextCompat.getColor(root.context, R.color.colorSecond)
                    else -> ContextCompat.getColor(root.context, R.color.red)
                }
                circularRatingMovie.setIndicatorColor(colorCircular)
                imgMovie.loadImage(movie?.posterUri, 20)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGridHorizontalMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = mListData.getOrNull(position)
        holder.setupView(movie)
    }

}