package com.bd_drmwan.moviesapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.core.base.BaseAdapter
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.moviesapp.databinding.ContainerListMovieBinding

class ListMovieAdapter: BaseAdapter<MovieModel, ListMovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ContainerListMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel?) {
            binding.apply {
                imgMovie.loadImage(movie?.posterUri, 10)
                tvTitle.text = movie?.title
                tvOverview.text = movie?.overview
                tvGenre.text = movie?.genre?.map { it?.name }?.joinToString(", ")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContainerListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mListData.getOrNull(position))
    }

}