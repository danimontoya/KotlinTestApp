package com.danieh.kotlintestapp.ui.main.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.danieh.kotlintestapp.R
import com.danieh.kotlintestapp.extensions.px

class DrawableDividerItemDecorator(val context: Context, val orientation: Orientation = Orientation.HORIZONTAL) : RecyclerView.ItemDecoration() {

    companion object {
        val INITIAL_SPACE = 200.px
    }

    private var fromBeginning = 0
    //By default, hide the last separator
    private var fromEnd = 1
    private var padding = 0

    private var dividerDrawable: Drawable? = ContextCompat.getDrawable(context, R.drawable.divider_yellow)

    constructor(context: Context, orientation: Orientation, @DrawableRes drawableId: Int?) : this(context, orientation) {
        dividerDrawable = if (drawableId != null) {
            ContextCompat.getDrawable(context, drawableId)
        } else {
            ContextCompat.getDrawable(context, R.drawable.divider_yellow)
        }
    }

    fun showFirst(showFirst: Boolean): DrawableDividerItemDecorator {
        fromBeginning = if (showFirst) 0 else 1
        return this
    }

    fun showLast(showLast: Boolean): DrawableDividerItemDecorator {
        fromEnd = if (showLast) 0 else 1
        return this
    }

    fun setSideMargin(paddingPx: Float): DrawableDividerItemDecorator {
        this.padding = paddingPx.toInt()
        return this
    }

    fun showFromBeginning(position: Int): DrawableDividerItemDecorator {
        fromBeginning = position
        return this
    }

    fun showFromEnd(position: Int): DrawableDividerItemDecorator {
        fromEnd = position
        return this
    }

    fun withDefaultSideMargin(): DrawableDividerItemDecorator {
        this.padding = context.resources.getDimension(R.dimen.side_margin).toInt()
        return this
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter ?: return

        val adapterSize = adapter.itemCount
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val adapterPosition = parent.getChildAdapterPosition(child)

            if (adapterPosition < fromBeginning) {
                continue
            }

            if (adapterPosition > adapterSize - 1 - fromEnd) {
                continue
            }

            drawDivider(c, child, getDrawable())
        }
    }

    private fun drawDivider(canvas: Canvas, item: View, drawable: Drawable?) {
        val left: Int
        val top: Int
        val right: Int
        val bottom: Int
        if (orientation == Orientation.HORIZONTAL) {
            left = (item.left.toFloat() + item.translationX + padding.toFloat()).toInt()
            top = (item.top + item.translationY).toInt()
            right = left + item.width - padding * 2
            bottom = top + item.height
            drawable?.setBounds(left, top + item.height - drawable.intrinsicHeight, right, bottom)
        } else {
            left = (item.left + item.translationX).toInt()
            top = (item.top.toFloat() + item.translationY + padding.toFloat()).toInt()
            right = left + item.width
            bottom = top + item.height - padding * 2
            drawable?.setBounds(left + item.width - drawable.intrinsicWidth, top, right, bottom)
        }

        drawable?.alpha = (item.alpha * 255).toInt()
        drawable?.draw(canvas)
    }

    private fun getDrawable(): Drawable? = dividerDrawable

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        if (parent?.getChildAdapterPosition(view) == 0) {
            outRect?.set(0, parent.measuredHeight - INITIAL_SPACE, 0, 0)
        } else {
            outRect?.setEmpty()
        }
    }

    enum class Orientation {
        HORIZONTAL, VERTICAL
    }
}