package com.example.myim.provider

import android.content.Context
import android.widget.Toast

object ToastProvider {

    fun showToast(context: Context, msg: String?) {
        if (msg.isNullOrBlank()) {
            return
        }
        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.show()
    }
}