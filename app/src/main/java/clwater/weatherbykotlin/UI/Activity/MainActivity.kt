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


        init()
    }

    private fun init() {
//        initRefresh()
        initview()
    }

    private fun initview() {
//        textview_main_citylist.setOnClickListener {  startActivity()}
    }

//
//    private fun initRefresh() {
//        swipeRefreshLayout_main.setOnRefreshListener {
//            async(){
//                Thread.sleep(2000)
//                Log.d("gzb" , "re")
//                uiThread{
//                    swipeRefreshLayout_main.isRefreshing = false
//                }
//            }
//        }
//        swipeRefreshLayout_main.setColorSchemeResources(android.R.color.holo_blue_bright,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light,
//                android.R.color.holo_red_light)
//    }


}

