package com.lukesmoljak.realestate.framework

import android.app.Application
import com.lukesmoljak.realestate.di.AppComponent
import com.lukesmoljak.realestate.di.DaggerAppComponent

open class BaseApplication: Application() {

    val appComponent: AppComponent by lazy {
        initAppComponent()
    }

    open fun initAppComponent(): AppComponent {
        return DaggerAppComponent.builder().application(this).build()
    }
}