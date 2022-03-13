package com.bd_drmwan.moviesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.moviesapp.databinding.ContainerListMovieBinding

class ListMovieAdapter : ListAdapter<MovieModel, ListMovieAdapter.ViewHolder>(diffUtilCallback) {

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

    inner class ViewHolder(private val binding: ContainerListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callback?.invoke(getItem(bindingAdapterPosition))
            }
        }

        fun bind(movie: MovieModel?) {
            binding.apply {
                imgMovie.loadImage(movie?.posterUri, 10)
                val title = "${movie?.title} (${movie?.releaseDate?.take(4) ?: "-"})"
                tvTitle.text = title
                tvOverview.text = movie?.overview ?: "No Description"
                tvGenre.text = movie?.genre?.map { it?.name }?.joinToString(", ") ?: "-"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ContainerListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<MovieModel>?) {
        super.submitList(list?.map { it.copy() })
    }

}