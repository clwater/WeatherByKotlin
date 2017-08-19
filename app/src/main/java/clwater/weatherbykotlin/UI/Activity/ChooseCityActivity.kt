package clwater.weatherbykotlin.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Window
import clwater.weatherbykotlin.EventBus.EB_changeCityTitle
import clwater.weatherbykotlin.Model.Province
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.UI.Adapter.CityChooseAdapter
import clwater.weatherbykotlin.UI.Adapter.ItemDecoration
import clwater.weatherbykotlin.Utils.Analysis
import clwater.weatherbykotlin.Utils.Request
import kotlinx.android.synthetic.main.activity_choosecity.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by gengzhibo on 17/8/14.
 */




class ChooseCityActivity :  AppCompatActivity(){

    var provinceListy = ArrayList<Province>()
    lateinit var _CityChooseAdapter : CityChooseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_choosecity)
        supportActionBar?.hide()

        EventBus.getDefault().register(this)

        initView()
        initData()
    }

    private fun initData() {
        doAsync {
            var cityListText = Request("http://i.tq121.com.cn/j/wap2016/news/city_data.js?2016").run()
            provinceListy = Analysis.analysisCityList(cityListText)
            uiThread {
                _CityChooseAdapter = CityChooseAdapter(this@ChooseCityActivity , provinceListy)
                recyview_chooseCity_recyview.adapter = _CityChooseAdapter
            }
        }
    }



    fun initView(){
//        textview_choooseCity_cityIndex1.setText("城市")
        recyview_chooseCity_recyview.layoutManager = LinearLayoutManager(this)
        recyview_chooseCity_recyview.addItemDecoration(ItemDecoration(this@ChooseCityActivity))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EventBus_changeCityTitle(e : EB_changeCityTitle){
        textview_choooseCity_cityIndex1.text = provinceListy.get(e.position).Pname
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}