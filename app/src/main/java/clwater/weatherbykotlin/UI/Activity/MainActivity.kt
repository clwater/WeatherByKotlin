package clwater.weatherbykotlin.UI.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.UI.Layout.MainActivityUI
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

//    lateinit var textview_main_cityname: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        MainActivityUI().setContentView(this@MainActivity)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()


        init()
    }

    private fun init() {
//        textview_main_cityname = find(R.id.textview_main_cityname)
//
//        textview_main_cityname.text = "ti"
    }




}
