package com.solodilov.newsapp

import android.app.Application
import com.solodilov.newsapp.di.DaggerApplicationComponent

class App : Application() {

    val appComponent = DaggerApplicationComponent.factory().create(this)

}