package com.example.baseapp_jetpackcompose.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle

object Utility {
    fun Activity.launchActivity(
        packageName: String,
        className: String,
        flags: Int = -1,
        bundle: Bundle? = null
    ){
        val intent = Intent(Intent.ACTION_VIEW).setClassName(packageName, className)
        if (flags != -1) intent.flags = flags
        if (bundle != null) intent.putExtras(bundle)
        startActivity(intent)
    }
}