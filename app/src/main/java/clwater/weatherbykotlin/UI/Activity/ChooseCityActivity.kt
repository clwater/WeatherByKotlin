package clwater.weatherbykotlin.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Window
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
    var cityShow = ArrayList<CityChooseListShowModel>()
    var index : Int = -1
    lateinit var _tempCityList : ArrayList<Province.City>

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
                for (index in 0 .. provinceListy.size - 1){
                    var cityChooseListShowModel : CityChooseListShowModel = CityChooseListShowModel()
                    cityChooseListShowModel.name = provinceListy.get(index).Pname
                    cityChooseListShowModel.id = index
                    cityShow.add(cityChooseListShowModel)
                }
                _CityChooseAdapter = CityChooseAdapter(this@ChooseCityActivity , cityShow)
                recyview_chooseCity_recyview.adapter = _CityChooseAdapter
            }
        }
    }



    fun initView(){
        recyview_chooseCity_recyview.layoutManager = LinearLayoutManager(this)
        recyview_chooseCity_recyview.addItemDecoration(ItemDecoration(this@ChooseCityActivity))
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EventBus_changeCityTitle(e : EB_changeCityTitle){
        textview_choooseCity_cityIndex1.text = provinceListy.get(e.position).Pname
        index = e.index
        cityShow.clear()

        if(index == 1){
            _tempCityList  = provinceListy.get(e.position).CityList as ArrayList<Province.City>
            for (index in 0 .. _tempCityList.size - 1){
                var cityChooseListShowModel  = CityChooseListShowModel()
                cityChooseListShowModel.name = _tempCityList[index].Cname
                cityChooseListShowModel.id = index
                cityShow.add(cityChooseListShowModel)
            }
            _CityChooseAdapter.index = 1
        }else if(index == 2){
            var _temp  = _tempCityList.get(e.position).RegionList
            for (index in 0 .. _temp.size - 1){
                var cityChooseListShowModel  = CityChooseListShowModel()
                cityChooseListShowModel.name = _temp[index].Rname
                cityChooseListShowModel.id = index
                cityShow.add(cityChooseListShowModel)
            }
        }

        _CityChooseAdapter.notifyDataSetChanged()



    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}