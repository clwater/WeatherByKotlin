package clwater.weatherbykotlin.UI.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.UI.Layout.MainActivityUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async

class MainActivity : AppCompatActivity() {

//    lateinit var textview_main_cityname: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        MainActivityUI().setContentView(this@MainActivity)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        startActivity<ChooseCityActivity>()
        init()
    }

    private fun init() {
//        initRefresh()
        initview()
    }

    private fun initview() {
        textview_main_citylist.setOnClickListener {  startActivity<ChooseCityActivity>()}

    }



}

