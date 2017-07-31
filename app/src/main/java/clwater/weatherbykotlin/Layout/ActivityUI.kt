package clwater.weatherbykotlin.Layout

import android.graphics.Color
import android.view.View
import clwater.weatherbykotlin.MainActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by gengzhibo on 17/7/31.
 */
class ActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui){
            verticalLayout {
                val textView=textView("TextView"){
                    textSize = sp(17).toFloat()
                    textColor= Color.parseColor("#00ff00")
                }.lparams{
                    margin=dip(10)
                    height= dip(40)
                    width= matchParent
                }
                val name = editText("EditText"){
                    hint = "hint"
                }
                button("Button") {
                    onClick { view ->
                        toast("Hello, ${name.text}!")
                    }
                }
            }
        }
    }

}