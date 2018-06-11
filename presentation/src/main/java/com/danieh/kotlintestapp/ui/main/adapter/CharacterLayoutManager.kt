package com.danieh.kotlintestapp.ui.main.adapter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


/**
 * Created by danieh
 */
class CharacterLayoutManager @JvmOverloads constructor(
        context: Context,
        @RecyclerView.Orientation orientation: Int = VERTICAL,
        reverseLayout: Boolean = false
) : LinearLayoutManager(context, orientation, reverseLayout) {

    companion object {
        val SCALE = 0.6f
        val ALPHA = 1.1f
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        if (orientation == LinearLayoutManager.VERTICAL) {
            val scrolled = super.scrollVerticallyBy(dy, recycler, state)
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val percentage = getPercentageFromBottom(child)
                val newScaleY = 1f - SCALE * percentage
                val newScaleX = 1f - SCALE * percentage
                val newAlpha = 1f - ALPHA * percentage

                (child as ViewGroup).getChildAt(0).apply {
                    scaleY = newScaleY
                    scaleX = newScaleX
                    alpha = newAlpha
                }
            }
            return scrolled
        } else {
            return 0
        }
    }

    private fun getPercentageFromBottom(child: View): Float {
        val bottomY = height.toFloat()
        val childCenterY = child.y + child.height / 2

        val offSet = Math.max(bottomY, childCenterY) - Math.min(bottomY, childCenterY)

        val maxOffset = height + child.height

        return offSet / maxOffset
    }

}