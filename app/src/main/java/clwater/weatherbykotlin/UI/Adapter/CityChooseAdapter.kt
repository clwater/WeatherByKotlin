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
    private var list = CityChooseListShowModel()
    private var index : Int

    constructor( context: Context, _list: CityChooseListShowModel , index : Int) {
        this.list = _list
        this.mLayoutInflater = LayoutInflater.from(context)
        this.index = index
    }



    override fun onBindViewHolder(holder: CityChooseViewHolder, position: Int) {
        this.index = list.index
        holder.text.text = list.list!![position]

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityChooseViewHolder {
        return CityChooseViewHolder(mLayoutInflater.inflate(R.layout.adapter_choosecity, parent, false))
    }



    override fun getItemCount(): Int {
        return if (list == null) 0 else list.list!!.size
    }


    inner class CityChooseViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView = view.findViewById(R.id.textview_chooseCityadapter_text)

        init {
            text.setOnClickListener( {
                EventBus.getDefault().post(EB_changeCityTitle(getPosition() , index))
            })
        }
    }
}
