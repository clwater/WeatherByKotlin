package clwater.weatherbykotlin.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.Window
import clwater.weatherbykotlin.EventBus.EB_ResultCityChoose
import clwater.weatherbykotlin.EventBus.EB_changeCityTitle
import clwater.weatherbykotlin.Model.CityChooseListShowModel
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
    var cityShow = CityChooseListShowModel()
    val listCityChooseListModel = ArrayList<String>()
    var index  = 0
    var index1  = 0
    var index2  = 0

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
            val cityListText = Request("http://i.tq121.com.cn/j/wap2016/news/city_data.js?2016").run()
            provinceListy = Analysis.analysisCityList(cityListText)
            uiThread {
                UpDataAdapter()
                _CityChooseAdapter = CityChooseAdapter(this@ChooseCityActivity , cityShow , index)
                recyview_chooseCity_recyview.adapter = _CityChooseAdapter
            }
        }
    }


    fun UpDataAdapter(){
        var listSize = -1
        if (index == 0 ){
            listSize = provinceListy.size
        } else if (index == 1) {
            listSize = provinceListy[index1].CityList.size
        }else if (index == 2){
            listSize = provinceListy[index1].CityList[index2].RegionList.size
        }

        listCityChooseListModel.clear()
        for (i in 0 .. listSize - 1){
            var cityChooseListModel  = String()
            if (index == 0 ){
                cityChooseListModel = provinceListy.get(i).Pname
            } else if (index == 1) {
                cityChooseListModel = provinceListy.get(index1).CityList[i].Cname
            }else if (index == 2){
                cityChooseListModel = provinceListy.get(index1).CityList[index2].RegionList[i].Rname
            }
            listCityChooseListModel.add(cityChooseListModel)
        }
        cityShow.index = index
        cityShow.list = listCityChooseListModel
    }

    fun initView(){
        recyview_chooseCity_recyview.layoutManager = LinearLayoutManager(this)
        recyview_chooseCity_recyview.addItemDecoration(ItemDecoration(this@ChooseCityActivity))

        textview_choooseCity_cityIndex1.setOnClickListener({
                index = -1
                EventBus.getDefault().post(EB_changeCityTitle(0 , index))
        })

        textview_choooseCity_cityIndex2.setOnClickListener({
            index = 0
            EventBus.getDefault().post(EB_changeCityTitle(index1 , index))
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EventBus_changeCityTitle(e : EB_changeCityTitle){
        index = e.index
        if (index == -1) {
            textview_choooseCity_cityIndex2.visibility = View.GONE
            textview_choooseCity_cityIndex3.visibility = View.GONE
        }else if (index == 0 ) {
            index1 = e.position
            textview_choooseCity_cityIndex2.visibility = View.VISIBLE
            textview_choooseCity_cityIndex2.text = provinceListy.get(index1).Pname
            textview_choooseCity_cityIndex3.visibility = View.GONE
        }else if (index == 1){
            index2 = e.position
            textview_choooseCity_cityIndex3.visibility = View.VISIBLE
            textview_choooseCity_cityIndex3.text = provinceListy.get(index1).CityList[index2].Cname
        }else if (index == 2){
            var cityName = provinceListy.get(index1).CityList[index2].RegionList[e.position].Rname
            var cityId = provinceListy.get(index1).CityList[index2].RegionList[e.position].Id
            this.finish()
            EventBus.getDefault().post(EB_ResultCityChoose(cityName , cityId))
        }
        index = index + 1
        UpDataAdapter()

        _CityChooseAdapter.notifyDataSetChanged()
        recyview_chooseCity_recyview.scrollToPosition(0)

    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onBackPressed() {

        if (index == 0){
            this.finish()
        }else if (index == 1 ){
            index = -1
            EventBus.getDefault().post(EB_changeCityTitle(0 , index))
        }else if (index ==2){
            index = 0
            EventBus.getDefault().post(EB_changeCityTitle(index1 , index))
        }
    }
}


