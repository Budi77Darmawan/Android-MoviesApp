package com.bd_drmwan.moviesapp.presentation.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.moviesapp.databinding.ContainerBannerHomeBinding
import io.github.vejei.carouselview.CarouselAdapter

class BannerAdapter : CarouselAdapter<BannerAdapter.ViewHolder>() {
    private var movies = mutableListOf<MovieModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(movies: List<MovieModel>?) {
        this.movies.clear()
        this.movies.addAll(movies ?: listOf())
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ContainerBannerHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieModel?) {
            binding.apply {
                imgBanner.loadImage(movie?.backdropUri)
                tvTitle.text = movie?.title ?: "-"
            }
        }
    }

    override fun onCreatePageViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ContainerBannerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindPageViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies.getOrNull(position))
    }

    override fun getPageCount(): Int = movies.size
}