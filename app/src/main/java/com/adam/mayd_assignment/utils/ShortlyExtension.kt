package com.adam.mayd_assignment.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

object ShortlyExtension {

    fun Context.copyToClipboard(text: CharSequence){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
    }

    fun View.applyText(text : String){

        when(this){
            is EditText -> this.setText(text)
            is TextView -> this.text = text
            is Button -> this.text = text
        }
    }
}