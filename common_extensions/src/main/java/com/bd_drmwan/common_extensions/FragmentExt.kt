package com.bd_drmwan.common_extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

fun Fragment.toast(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.horizontalLinearLayoutManager() =
    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

fun Fragment.verticalLinearLayoutManager() =
    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

fun Fragment.verticalGridLayoutManager(spanCount: Int) =
    GridLayoutManager(activity, spanCount, LinearLayoutManager.VERTICAL, false)