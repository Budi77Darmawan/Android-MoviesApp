package com.bd_drmwan.moviesapp.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.common_extensions.loadImage
import com.bd_drmwan.core.base.BaseAdapter
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.moviesapp.databinding.ItemGridHorizontalActorBinding


class GridActorsAdapter : BaseAdapter<CastModel, GridActorsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemGridHorizontalActorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                callbackRoot?.invoke(mListData.getOrNull(bindingAdapterPosition))
            }
        }

        fun setupView(cast: CastModel?) {
            with(binding) {
                setupHorizontalView(bindingAdapterPosition, root, root.context)
                tvNameActor.text = cast?.name ?: "Unknown"
                imgActor.loadImage(cast?.imageUri, 20)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemGridHorizontalActorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = mListData.getOrNull(position)
        holder.setupView(actor)
    }

}