package com.lukesmoljak.realestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lukesmoljak.realestate.framework.BaseApplication
import com.lukesmoljak.realestate.framework.presentation.MyFragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: MyFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        setFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun inject() {
        (application as BaseApplication).appComponent.inject(this)
    }

    private fun setFragmentFactory() {
        supportFragmentManager.fragmentFactory = fragmentFactory
    }
}