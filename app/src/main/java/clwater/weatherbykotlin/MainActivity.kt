package clwater.weatherbykotlin

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.jvm.javaClass
import kotlin.jvm.javaClass


class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        message.text = add2(2 , 3).toString()

        nicetoast("hello")
//        nicetoast("hello2" , "MyTag")
//        nicetoast("hello" , "MyTag" , Toast.LENGTH_SHORT)

    }

    fun add( x : Int , y : Int) : Int{
        return  x + y
    }

    fun add2( x: Int , y: Int) :Int = x + y

//    无法引入1.0已经弃用javaClass<T>
    fun nicetoast(message : String, tag :String = String::class.java.toString(), length : Int = Toast.LENGTH_SHORT){
        Toast.makeText(this, "[$tag] $message" ,length).show()
    }

}
