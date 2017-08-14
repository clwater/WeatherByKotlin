package clwater.weatherbykotlin.UI.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import clwater.weatherbykotlin.R

/**
 * Created by gengzhibo on 17/8/14.
 */



//for (int i = 0 ; i < 5 ; i++) {
//
//    TextView textView = new TextView(this);
//    textView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
//    textView.setCompoundDrawablePadding(12);
//    textView.setText("some advice text " + i);
//    textView.setTextSize(16);
//    textView.setTextColor(Color.parseColor("#666666"));
//    lineralayout_dashboard_suggest.addView(textView);
//}

class ChooseCityActivity :  AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        MainActivityUI().setContentView(this@MainActivity)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}