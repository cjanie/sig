package com.android.sig.utils

import android.content.Context
import android.widget.Toast

class MessageHandler {

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}