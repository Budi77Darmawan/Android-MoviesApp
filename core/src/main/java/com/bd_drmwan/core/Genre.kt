package com.bd_drmwan.core

import com.bd_drmwan.core.main.domain.model.Genre

object Genres {
    private var genres = listOf<Genre>()

    fun getData() = genres
    fun setData(data: List<Genre>?) {
        data?.let { genres = data }
    }
}