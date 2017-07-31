package clwater.weatherbykotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import clwater.weatherbykotlin.EventBus.EB_Test
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}

