package com.bd_drmwan.common_extensions

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


fun Activity.makeStatusBarTransparent() {
    window.apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            setDecorFitsSystemWindows(false)
            statusBarColor = Color.TRANSPARENT
        } else {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }
}

fun Activity.toast(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Activity.toastLong(message: String? = getString(R.string.default_message_toast)) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Activity.hideSoftKeyboard(mView: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(mView.windowToken, 0)
}