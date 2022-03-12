package com.bd_drmwan.core.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bd_drmwan.core.utils.DisplayScreen
import com.bd_drmwan.core.utils.toPx

abstract class BaseAdapter<D: Any, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    protected var mListData = mutableListOf<D>()
    private var callbackRoot: ((D?) -> Unit)? = null

    open fun onRootClicked(callback: (D?) -> Unit) {
        callbackRoot = callback
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun setData(listData: List<D>?) {
        listData?.let {
            mListData.clear()
            mListData.addAll(it)
            notifyDataSetChanged()
        }
    }

    protected fun setupHorizontalView(adapterPosition: Int, rootView: View, mContext: Context) {
        val layoutParams = rootView.layoutParams as? ViewGroup.MarginLayoutParams
        val screenWidth = DisplayScreen.getScreenWidth(mContext)
        val itemWidth = screenWidth / 2.7
        layoutParams?.width = itemWidth.toInt()
        rootView.layoutParams = layoutParams

        val lastPosition = mListData.size - 1
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

    override fun getItemCount(): Int {
        return mListData.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            callbackRoot?.invoke(mListData.getOrNull(position))
        }
    }
}