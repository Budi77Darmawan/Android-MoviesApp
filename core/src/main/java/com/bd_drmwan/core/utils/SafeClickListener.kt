package com.bd_drmwan.core.utils

import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SafeClickListener(
    lifecycle: Lifecycle,
    private val onSafeCLick: (View?) -> Unit
) : View.OnClickListener {
    private var onClicked = false
    private val debouncePeriod = 600L
    private val coroutineScope = lifecycle.coroutineScope
    private var onClickListener: Job? = null

    override fun onClick(v: View?) {
        onClickListener?.cancel()
        onClickListener = coroutineScope.launch {
            if (!onClicked) {
                onClicked = true
                onSafeCLick(v)
            }
            delay(debouncePeriod)
            onClicked = false
            onClickListener?.cancel()
        }
    }
}

fun View.setSafeOnClickListener(
    lifecycle: Lifecycle,
    onSafeClick: (View?) -> Unit
) {
    val safeClickListener = SafeClickListener(lifecycle) {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
