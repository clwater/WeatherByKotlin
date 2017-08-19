package clwater.weatherbykotlin.UI.Adapter

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by yszsyf on 2017/8/19.
 */

class ItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val mDivider: Drawable?

    init {
        val array = context.obtainStyledAttributes(ATTRS)
        // 获取分隔条
        mDivider = array.getDrawable(0)
        array.recycle()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        val count = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        for (i in 0..count - 1) {
            val v = parent.getChildAt(i)
            val params = v.layoutParams as RecyclerView.LayoutParams
            val top = v.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c)
        }
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}