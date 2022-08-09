package com.adam.mayd_assignment.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object ShortlyExtension {

    fun Context.copyToClipboard(text: CharSequence){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
    }
}