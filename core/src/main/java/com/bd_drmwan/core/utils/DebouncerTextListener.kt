package com.bd_drmwan.core.utils

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DebouncerTextListener(
    lifecycle: Lifecycle,
    timeMillis: Long = 500L,
    val onDebouncingQueryTextChange: (String?) -> Unit
) : TextWatcher,
    LifecycleObserver {
    private val debouncePeriod = timeMillis
    private val coroutineScope = lifecycle.coroutineScope
    private var searchJob: Job? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        searchJob?.cancel()
    }

    override fun afterTextChanged(s: Editable?) {
        val str = s.toString()
        searchJob = coroutineScope.launch {
            delay(debouncePeriod)
            onDebouncingQueryTextChange(str)
        }
    }
}
