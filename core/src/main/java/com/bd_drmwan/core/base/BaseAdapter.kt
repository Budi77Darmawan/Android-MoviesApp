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
    protected var mRecyclerView: RecyclerView? = null

    protected var callbackRoot: ((D?) -> Unit)? = null

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

    @SuppressLint("NotifyDataSetChanged")
    open fun addData(listData: List<D>) {
        mListData.addAll(listData.toMutableList())
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    open fun clearData() {
        mListData.clear()
        notifyDataSetChanged()
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

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }
}