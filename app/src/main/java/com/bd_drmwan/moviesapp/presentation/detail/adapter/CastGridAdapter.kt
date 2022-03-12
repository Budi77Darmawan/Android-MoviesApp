package com.bd_drmwan.moviesapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.common_extensions.loadImageCircleCrop
import com.bd_drmwan.core.base.BaseAdapter
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.moviesapp.databinding.ContainerItemListCastBinding


class CastGridAdapter : BaseAdapter<CastModel, CastGridAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ContainerItemListCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: CastModel?) {
            binding.apply {
                imgCast.loadImageCircleCrop(cast?.imageUri)
                tvFullName.text = cast?.name
                tvCastName.text = cast?.characterName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ContainerItemListCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(mListData.getOrNull(position))
    }
}