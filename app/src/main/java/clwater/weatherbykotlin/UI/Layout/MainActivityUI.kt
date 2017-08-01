package clwater.weatherbykotlin.UI.Layout

import android.view.Gravity
import android.view.View
import clwater.weatherbykotlin.R
import clwater.weatherbykotlin.UI.Activity.MainActivity
import org.jetbrains.anko.*

/**
 * Created by gengzhibo on 17/7/31.
 */
class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui){
            relativeLayout {

                var toolbar_main = toolbar {
                    var textview_main_cityname = textView(){
                        id = R.id.textview_main_cityname
                    }

                }.lparams{
                    width = matchParent
                    gravity = Gravity.CENTER
                }


                var rela_main_night_parent =  relativeLayout {
                    val relativeLayout_night_text = textView("a\na") {
                        id = R.id.rela_main_night_parent
                    }

                    val rela_main_night_weatherparent = verticalLayout{

                        linearLayout {
                            val rela_main_night_weather = textView("text1"){
                                id = R.id.rela_main_night_weather
                                gravity = Gravity.CENTER
                            }.lparams{
                                width = 0
                                weight = 1f
                                height = wrapContent
                            }

                            val rela_main_night_temp = textView("text2"){
                                id = R.id.rela_main_night_temp
                                gravity = Gravity.CENTER

                            }.lparams{
                                width = 0
                                weight = 1f
                                height = wrapContent
                            }
                        }

                        linearLayout {
                            val rela_main_night_windd = textView("text3"){
                                id = R.id.rela_main_night_windd
                                gravity = Gravity.CENTER
                            }.lparams{
                                width = 0
                                weight = 1f
                                height = wrapContent
                            }

                            val rela_main_night_windp = textView("text4"){
                                id = R.id.rela_main_night_windp
                                gravity = Gravity.CENTER
                            }.lparams{
                                width = 0
                                weight = 1f
                                height = wrapContent
                            }
                        }


                    }.lparams{
                        rightOf(relativeLayout_night_text)
                        height = wrapContent
                        width = matchParent
                    }
                }.lparams{
                    alignParentBottom()
                    width = matchParent
                    height = wrapContent
                    margin = dip(10)
                }


            }

        }
    }

}