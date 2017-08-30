package clwater.weatherbykotlin.UI.Activity

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import clwater.weatherbykotlin.EventBus.EB_ResultCityChoose
import clwater.weatherbykotlin.Model.WeatherModel
import clwater.weatherbykotlin.Model.WeatherNowModel
import clwater.weatherbykotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.*
import clwater.weatherbykotlin.Utils.*
import com.bumptech.glide.Glide



class MainActivity : AppCompatActivity() {

    private var cityName: String by Preference(this, "cityName", "北京")
    private var cityId : String by Preference(this, "cityId", "101010100")

    private lateinit var weatherList : ArrayList<WeatherModel>
    private lateinit var weathernow : WeatherNowModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)
        supportActionBar?.hide()

        checkChooseData()
        initView()
    }

    private fun initView() {
        initChoose()
        initScroll()
    }


    private fun initChoose() {
        textview_main_citylist.setOnClickListener {  startActivity<ChooseCityActivity>()}
    }

    private fun initScroll() {
        swipeRefreshLayout_main.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED)
        swipeRefreshLayout_main.setOnRefreshListener({updataWeatherData()})
    }

    private fun checkChooseData(){
        if (!cityName.equals("")){
            textview_main_cityname.text = cityName
            updataWeatherData()
        } else{
            startActivity<ChooseCityActivity>()
        }
    }

    private fun updataWeatherData() {
        swipeRefreshLayout_main.isRefreshing = true
        doAsync {
            val geoList = getCityGeo()
            val url = String.format("http://m.weather.com.cn/d/town/index?lat=%s&lon=%s" , geoList.get(1) , geoList.get(0) )
            val cityInfoText = Request(url).run()
            weatherList = Analysis.analysisCityInfo(cityInfoText)
            weathernow =  Analysis.analysisCityNow(cityInfoText)
            uiThread {
                updataView()
                swipeRefreshLayout_main.isRefreshing = false
            }
        }
    }

    private fun updataView() {
        updataNowView()
        updataAfterView()
        updataBackground()
        updataDataText()
    }

    private fun updataDataText() {
        textview_d2_text.text = afterWeek(Times.getWeek() , 2)
        textview_d3_text.text = afterWeek(Times.getWeek() , 3)
        textview_d4_text.text = afterWeek(Times.getWeek() , 4)
    }

    private fun afterWeek(week: String, add: Int): String? {
        var index = 0
        when(week){
            "周一" -> index = 0
            "周二" -> index = 1
            "周三" -> index = 2
            "周四" -> index = 3
            "周五" -> index = 4
            "周六" -> index = 5
            "周日" -> index = 6
        }
        index = index + add
        index = index % 7

        when(index){
            0 -> return "周一"
            1 -> return "周二"
            2 -> return "周三"
            3 -> return "周四"
            4 -> return "周五"
            5 -> return "周六"
            6 -> return "周日"
            else -> return ""
        }
    }

    private fun updataBackground() {
        val url: String

        if(Times.getHour() <= 18 && Times.getHour() > 6) {
            url = String.format("http://i.tq121.com.cn/i/wap2017/bg/d%s.jpg", TextUtils.text2index(weathernow.weather))
        }else{
            url = String.format("http://i.tq121.com.cn/i/wap2017/bg/n%s.jpg", TextUtils.text2index(weathernow.weather))
        }
        imageview_main_bg.alpha = 0.5f
        Glide.with(this)
                .load(url)
                .into(imageview_main_bg)
    }

    private fun updataAfterView() {
        val after0 = weatherList.get(0)
        val after1 = weatherList.get(1)
        val after2 = weatherList.get(2)
        val after3 = weatherList.get(3)
        val after4 = weatherList.get(4)

        textview_d0_w0.text = TextUtils.index2text(after0.WEATHER1)
        textview_d0_w1.text = TextUtils.index2text(after0.WEATHER2)
        textview_d0_i0.background = getDrawable(index2image(after0.WEATHER1))
        textview_d0_i1.background = getDrawable(index2image(after0.WEATHER2))
        textview_d0_t0.text = weatherList.get(0).TEMMAX.toString()
        textview_d0_t1.text = weatherList.get(0).TEMMIN.toString()
        textview_d0_wind.text = getWindtext(after0)

        textview_d1_w0.text = TextUtils.index2text(after1.WEATHER1)
        textview_d1_w1.text = TextUtils.index2text(after1.WEATHER2)
        textview_d1_i0.background = getDrawable(index2image(after1.WEATHER1))
        textview_d1_i1.background = getDrawable(index2image(after1.WEATHER2))
        textview_d1_t0.text = after1.TEMMAX.toString()
        textview_d1_t1.text = after1.TEMMIN.toString()
        textview_d1_wind.text = getWindtext(after1)

        textview_d2_w0.text = TextUtils.index2text(after2.WEATHER1)
        textview_d2_w1.text = TextUtils.index2text(after2.WEATHER2)
        textview_d2_i0.background = getDrawable(index2image(after2.WEATHER1))
        textview_d2_i1.background = getDrawable(index2image(after2.WEATHER2))
        textview_d2_t0.text = after2.TEMMAX.toString()
        textview_d2_t1.text = after2.TEMMIN.toString()
        textview_d2_wind.text = getWindtext(after2)

        textview_d3_w0.text = TextUtils.index2text(after3.WEATHER1)
        textview_d3_w1.text = TextUtils.index2text(after3.WEATHER2)
        textview_d3_i0.background = getDrawable(index2image(after3.WEATHER1))
        textview_d3_i1.background = getDrawable(index2image(after3.WEATHER2))
        textview_d3_t0.text = after3.TEMMAX.toString()
        textview_d3_t1.text = after3.TEMMIN.toString()
        textview_d3_wind.text = getWindtext(after3)

        textview_d4_w0.text = TextUtils.index2text(after4.WEATHER1)
        textview_d4_w1.text = TextUtils.index2text(after4.WEATHER2)
        textview_d4_i0.background = getDrawable(index2image(after4.WEATHER1))
        textview_d4_i1.background = getDrawable(index2image(after4.WEATHER2))
        textview_d4_t0.text = after4.TEMMAX.toString()
        textview_d4_t1.text = after4.TEMMIN.toString()
        textview_d4_wind.text = getWindtext(after4)
    }


    private fun updataNowView() {
        textview_now_updata.setText(weathernow.updataTime)
        textview_now_temp.setText(weathernow.temp + "°")
        textview_now_weather.setText(weathernow.weather)
        textview_now_wind.setText(weathernow.wind)
        textview_now_wet.setText(weathernow.wet)
    }

    private fun getCityGeo() :List<String>{
        val url = String.format("http://mobile.weather.com.cn/data/forecast/%s.html", cityId)
        val cityInfoText = Request(url).run()
        return Analysis.analysisCityGeo(cityInfoText)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun EB_ResultCityChoose(e: EB_ResultCityChoose){
        cityName = e.name
        cityId = e.id

        textview_main_cityname.setText(cityName)
        updataWeatherData()
    }

    private fun index2image(index: String): Int {

        val field = R.drawable::class.java.getField("icon_weather_" + index)
        val i = field.getInt(R.drawable())
        return i

    }

    private fun getWindtext(weatherModel: WeatherModel ):String{
        if(weatherModel.WIND1.equals(weatherModel.WIND2) && weatherModel.WINS1.equals(weatherModel.WINS2)) {
            return TextUtils.index2WindDtext(weatherModel.WIND1) + TextUtils.index2WindLtext(weatherModel.WINS1)
        }else{
            return TextUtils.index2WindDtext(weatherModel.WIND1) + TextUtils.index2WindLtext(weatherModel.WINS1) + "转" +
                    TextUtils.index2WindDtext(weatherModel.WIND2) + TextUtils.index2WindLtext(weatherModel.WINS2)
        }
    }

}

