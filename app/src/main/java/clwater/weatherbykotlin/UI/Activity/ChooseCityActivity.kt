package clwater.weatherbykotlin.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Window
import clwater.weatherbykotlin.Model.Province
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.Utils.Analysis
import clwater.weatherbykotlin.Utils.Request
import kotlinx.android.synthetic.main.activity_choosecity.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by gengzhibo on 17/8/14.
 */




class ChooseCityActivity :  AppCompatActivity(){

    var provinceListy = ArrayList<Province>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_choosecity)

        supportActionBar?.hide()

        initView()
        initData()
    }

    private fun initData() {
        doAsync {
            var cityListText = Request("http://i.tq121.com.cn/j/wap2016/news/city_data.js?2016").run()
            provinceListy = Analysis.analysisCityList(cityListText)
            uiThread { Log.d("gzb" , provinceListy.get(5).Pname) }
        }
    }


    fun initView(){
        textview_choooseCity_cityIndex1.setText("城市")
    }
}