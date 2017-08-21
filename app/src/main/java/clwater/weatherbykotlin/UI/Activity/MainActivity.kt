package clwater.weatherbykotlin.UI.Activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import clwater.weatherbykotlin.EventBus.EB_ResultCityChoose
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.UI.Layout.MainActivityUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.async

class MainActivity : AppCompatActivity() {

    private var cityName = String()
    private var cityId = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EB_ResultCityChoose(e: EB_ResultCityChoose){
        cityName = e.name
        cityId = e.id

        textview_main_cityname.setText(cityName)
    }


}

