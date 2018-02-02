package com.acv.marvel.app.view.common

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import com.acv.marvel.R

data class DividerDecorationK(
        val color: Int,
        val with: Float
) : RecyclerView.ItemDecoration() {
    var paint: Paint = Paint()

    init {
        paint.color = color
        paint.strokeWidth = with
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val offset = (paint.strokeWidth / 2).toInt()

        val max = parent.childCount
        for (i in 0 until max) {
            val view = parent.getChildAt(i)

            if (view.id == R.id.itemContainer
                    && i < max - 1
                    && parent.getChildAt(i + 1).id == view.id) {
                val position = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition

                if (position < state.itemCount) {
                    paint.alpha = (view.alpha * paint.alpha).toInt()
                    drawSeparator(view, offset, c, paint)
                }
            }
        }
    }

    private fun drawSeparator(view: View, offset: Int, canvas: Canvas, paint: Paint) {
        with(view) {
            canvas.drawLine(
                    getStartX(view),
                    getStartY(view, offset.toFloat()),
                    getStopX(view),
                    getStopY(view, offset.toFloat()),
                    paint
            )
        }
    }

    fun getStartX(v: View) = with(v) { left + translationX }

    fun getStopX(v: View) = with(v) { right + translationX }

    fun getStartY(v: View, offset: Float) = with(v) { bottom.toFloat() + offset + translationY }

    fun getStopY(v: View, offset: Float) = with(v) { bottom.toFloat() + offset + translationY }


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition

        if (position < state.itemCount) {
            outRect.set(0, 0, 0, paint.strokeWidth.toInt())
        } else {
            outRect.setEmpty()
        }
    }

    class Builder(context: Context) {
        private val mResources: Resources = context.resources
        private var mHeight: Int = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_PX,
                1f,
                context.resources.displayMetrics
        ).toInt()
        private var mLPadding: Int = 0
        private var mRPadding: Int = 0
        private var mColour: Int = Color.BLACK

        fun setHeight(pixels: Float) = apply {
            mHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixels, mResources.displayMetrics).toInt()
        }

        fun setHeight(@DimenRes resource: Int) = apply {
            mHeight = mResources.getDimensionPixelSize(resource)
        }

        fun setPadding(pixels: Float) = apply {
            setLeftPadding(pixels)
            setRightPadding(pixels)
        }

        fun setPadding(@DimenRes resource: Int) = apply {
            setLeftPadding(resource)
            setRightPadding(resource)
        }

        fun setLeftPadding(pixelPadding: Float) = apply {
            mLPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixelPadding, mResources.displayMetrics).toInt()
        }

        fun setRightPadding(pixelPadding: Float) = apply {
            mRPadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pixelPadding, mResources.displayMetrics).toInt()
        }

        fun setLeftPadding(@DimenRes resource: Int) = apply {
            mLPadding = mResources.getDimensionPixelSize(resource)
        }

        fun setRightPadding(@DimenRes resource: Int) = apply {
            mRPadding = mResources.getDimensionPixelSize(resource)
        }

        fun setColorResource(@ColorRes resource: Int) = apply {
            setColor(mResources.getColor(resource))
        }

        fun setColor(@ColorInt color: Int) = apply {
            mColour = color
        }
    }
}
