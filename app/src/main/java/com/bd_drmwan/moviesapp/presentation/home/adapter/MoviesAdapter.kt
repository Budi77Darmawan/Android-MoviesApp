package com.bd_drmwan.moviesapp.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.utils.DisplayScreen
import com.bd_drmwan.core.utils.toPx
import com.bd_drmwan.moviesapp.R
import com.bd_drmwan.moviesapp.databinding.ItemGridHorizontalMovieBinding

class MoviesAdapter(
    private val isLinearLayout: Boolean = false
) : ListAdapter<MovieModel, MoviesAdapter.ViewHolder>(diffUtilCallback) {

    private var callback: ((MovieModel?) -> Unit)? = null

    fun onRootClicked(callback: (MovieModel?) -> Unit) {
        this.callback = callback
    }

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(private val binding: ItemGridHorizontalMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback?.invoke(getItem(bindingAdapterPosition))
            }
        }

        fun bind(movie: MovieModel?) {
            with(binding) {
                if (isLinearLayout) {
                    setupHorizontalView(bindingAdapterPosition, root, root.context)
                }

                tvTitleMovie.text = movie?.title ?: "-"
                tvRating.text = movie?.voteAverage?.toDouble()?.toString()
                val rate = ((movie?.voteAverage?.toDouble() ?: 0.0) * 10).toInt()
                circularRatingMovie.progress = rate
                val colorCircular = when {
                    rate >= 75 -> androidx.core.content.ContextCompat.getColor(
                        root.context,
                        R.color.green
                    )
                    rate >= 60 -> androidx.core.content.ContextCompat.getColor(
                        root.context,
                        R.color.colorSecond
                    )
                    else -> androidx.core.content.ContextCompat.getColor(root.context, R.color.red)
                }
                circularRatingMovie.setIndicatorColor(colorCircular)
                imgMovie.loadImage(movie?.posterUri, 20)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGridHorizontalMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<MovieModel>?) {
        super.submitList(list?.map { it.copy() })
    }

    private fun setupHorizontalView(adapterPosition: Int, rootView: View, mContext: Context) {
        val layoutParams = rootView.layoutParams as? ViewGroup.MarginLayoutParams
        val screenWidth = DisplayScreen.getScreenWidth(mContext)
        val itemWidth = screenWidth / 2.7
        layoutParams?.width = itemWidth.toInt()
        rootView.layoutParams = layoutParams

        val lastPosition = itemCount
        when {
            adapterPosition == 0 -> {
                layoutParams?.marginStart = 0.toPx()
                layoutParams?.marginEnd = 7.toPx()
                rootView.layoutParams = layoutParams
            }
            lastPosition == adapterPosition -> {
                layoutParams?.marginStart = 7.toPx()
                layoutParams?.marginEnd = 0.toPx()
                rootView.layoutParams = layoutParams
            }
            else -> {
                layoutParams?.marginStart = 7.toPx()
                layoutParams?.marginEnd = 7.toPx()
                rootView.layoutParams = layoutParams
            }
        }
    }
}