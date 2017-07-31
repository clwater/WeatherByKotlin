package clwater.weatherbykotlin.UI.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import clwater.weatherbykotlin.UI.Layout.MainActivityUI
import org.jetbrains.anko.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this@MainActivity)

    }





}

