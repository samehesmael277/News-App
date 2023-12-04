package com.sameh.news.utils

import android.util.Log

fun String.toLogD(tag: String = "DebuggingTAG") {
    Log.d(tag, this)
}

fun String.toLogE(tag: String = "DebuggingTAG") {
    Log.e(tag, this)
}

fun String.toLogW(tag: String = "DebuggingTAG") {
    Log.w(tag, this)
}