package com.adam.mayd_assignment.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MAYD_ApplicationClass : Application() {

    override fun onCreate() { super.onCreate() }
}