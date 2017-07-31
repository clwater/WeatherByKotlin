package clwater.weatherbykotlin.UI.Layout

import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import clwater.weatherbykotlin.UI.Activity.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by gengzhibo on 17/7/31.
 */
class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui){
            relativeLayout {


                var textview_main_cityname = textView("Cityname"){
//                    textSize = sp(18).toFloat()
                }.lparams{
                    width = wrapContent
                    topMargin = dip(10)
                }

                var textview_main_weather = textView("Weather"){
//                    textSize = sp(16).toFloat()
                    textColor = Color.parseColor("#323232")
                }.lparams{
                    rightOf(textview_main_cityname)

                    width = matchParent

                    leftMargin = dip(10)
                }

//                var relativeLayout_main_nightParent = relativeLayout {
//
//                    val textView_main_nightParent_text = textView("今\n夜"){
//                        textSize = sp(16).toFloat()
//                    }.lparams{
//                        width = wrapContent
//                        height = wrapContent
//                        leftMargin = dip(10)
//                    }
//
//
//                    var linearLayout_main_nightParent_infoParent = linearLayout{
//                        var textView_main_nightParent_weathertext = textView("天气"){
//
//                        }
//                    }.lparams{
//                        width = matchParent
//                        height = wrapContent
//                        rightOf(textView_main_nightParent_text)
//                    }
//
//
//                }.lparams{
//                    width = matchParent
//                    height = wrapContent
//                    bottomMargin = dip(10)
//                    topMargin = dip(10)
//                    alignParentBottom()
//
//                }

            }

        }
    }

}