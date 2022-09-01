package com.bd_drmwan.core.enums

enum class MoviesType(var value: String, var path: String) {
    UPCOMING("Up Coming", "upcoming"),
    NOW_PLAYING("Now Playing", "now_playing"),
    TOP_RATED("Top Rated", "top_rated"),
    POPULAR("Popular", "popular")
}