package com.fancy.instagram.application

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

class InstagramApp: Application() {
    companion object {
        lateinit var appContext: InstagramApp
        var runningActivitys: Int = 0

        fun string(@StringRes resId: Int): String {
            return appContext.getString(resId)
        }

        fun color(@ColorRes resId: Int): Int {
            return ContextCompat.getColor(appContext, resId)
        }

        fun arrays(@ArrayRes resId: Int): Array<String> {
            return resources().getStringArray(resId)
        }

        fun resources(): Resources {
            return appContext.resources
        }

        fun drawable(@DrawableRes resId: Int): Drawable? {
            return ContextCompat.getDrawable(appContext, resId)
        }

        fun packageName(): String {
            return appContext.packageName
        }

        fun isEnabledNetwork(): Boolean {
            return try {
                val cm = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val activeNetwork = cm.activeNetworkInfo
                activeNetwork.isConnected
            } catch (e: Exception) {
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this
    }
}