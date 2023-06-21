package com.allen.weather.ui.common.recyclerview

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.allen.weather.R

class DividerDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    private val paint: Paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
        color = ContextCompat.getColor(context, R.color.grey)
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val layoutManager = parent.layoutManager as? LinearLayoutManager ?: return

        for (index in 0 until layoutManager.itemCount - 1) {
            layoutManager.getChildAt(index)?.let {
                canvas.drawRect(it.left.toFloat(), it.bottom - DP, it.right.toFloat(), it.bottom.toFloat(), paint)
            }
        }
    }

    companion object {
        private val DP: Float = Resources.getSystem().displayMetrics.density
    }

}