package clwater.weatherbykotlin.UI.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import clwater.weatherbykotlin.EventBus.EB_changeCityTitle
import clwater.weatherbykotlin.Model.CityChooseListShowModel
import clwater.weatherbykotlin.Model.Province
import clwater.weatherbykotlin.R
import org.greenrobot.eventbus.EventBus

/**
 * Created by yszsyf on 2017/8/18.
 */
class CityChooseAdapter : RecyclerView.Adapter<CityChooseAdapter.CityChooseViewHolder> {


    private val mLayoutInflater: LayoutInflater
    private var list: List<CityChooseListShowModel>? = ArrayList<CityChooseListShowModel>()
    public var index = -1

    constructor( context: Context, _list: List<CityChooseListShowModel>) {
        this.list = _list
        this.mLayoutInflater = LayoutInflater.from(context)
        this.index = index + 1
    }



    override fun onBindViewHolder(holder: CityChooseViewHolder, position: Int) {
        holder.text.text = list!![position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityChooseViewHolder {
        return CityChooseViewHolder(mLayoutInflater.inflate(R.layout.adapter_choosecity, parent, false))
    }



    override fun getItemCount(): Int {
        return if (list == null) 0 else list!!.size
    }


    inner class CityChooseViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView = view.findViewById(R.id.textview_chooseCityadapter_text)

        init {
            text.text = "set CityName"
            text.setOnClickListener( {
                Log.d("gzb" , "onclick" + getPosition())
                EventBus.getDefault().post(EB_changeCityTitle(getPosition() , index + 1))
            })
        }
    }
}
