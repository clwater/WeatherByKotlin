package clwater.weatherbykotlin

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import clwater.weatherbykotlin.domain.RequestForecastCommand
import clwater.weatherbykotlin.domain.model.Forecast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.time.LocalDate
import java.util.*
import kotlin.jvm.javaClass
import kotlin.jvm.javaClass


class MainActivity() : AppCompatActivity() {



    //val date: Date , val temperature : Float , val  datails : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecastList.layoutManager = LinearLayoutManager(this)


        async{
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                //                forecast_list.adapter = ForecastListAdapter(result, object : OnItemClickListener{
//                    override fun invoke(forecast: Forecast) {
//                        toast(forecast.date)
//                    }
//                })

                val adapter = ForecastListAdapter(result) {  toast(it.date) }
                forecastList.adapter = adapter
            }
        }


        startActivity(Intent(this , DetailActivity::class.java))


    }





}
