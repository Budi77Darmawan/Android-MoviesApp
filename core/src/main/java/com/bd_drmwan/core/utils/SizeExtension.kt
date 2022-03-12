package com.bd_drmwan.core.utils

import android.content.res.Resources

fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}