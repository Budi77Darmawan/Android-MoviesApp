package com.bd_drmwan.common_extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

fun Fragment.toast(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

fun Fragment.toastLong(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()

fun Fragment.hideSoftKeyboard(mView: View) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(mView.windowToken, 0)
}

fun Fragment.horizontalLinearLayoutManager() =
    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

fun Fragment.verticalLinearLayoutManager() =
    LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

fun Fragment.verticalGridLayoutManager(spanCount: Int) =
    GridLayoutManager(activity, spanCount, LinearLayoutManager.VERTICAL, false)

fun Fragment.horizontalGridLayoutManager(spanCount: Int) =
    GridLayoutManager(activity, spanCount, LinearLayoutManager.HORIZONTAL, false)