package com.houxinwu.viewpagerpagetransformer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

/**
 * Created by houxinwu on 2017/9/16.
 */
class ViewPagerIndicator : View {
    private lateinit var paint: Paint
    private lateinit var center: PointF

    private var currentPosition = 0
    private var offset = 0f

    private val itemHeight = 10f
    private val itemWidth = 50f

    var itemCount = 0

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    fun init() {
        paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND

        center = PointF()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        center.x = w.toFloat() / 2
        center.y = h.toFloat() / 2
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), itemHeight.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val width = getBackgroundWidth()
        paint.color = Color.parseColor("#d8d8d8")
        paint.strokeWidth = itemHeight - 2f
        canvas?.drawLine(center.x - width / 2, center.y, center.x + width/ 2, center.y, paint)

        paint.color = Color.parseColor("#f32020")
        paint.strokeWidth = itemHeight
        val startX = center.x - width / 2 + itemWidth * (currentPosition % itemCount + offset)
        canvas?.drawLine(startX, center.y, startX + itemWidth, center.y, paint)

    }

    private fun getBackgroundWidth(): Float {
        return itemWidth * itemCount
    }

    fun setCurrent(position: Int, offset: Float) {
        this.currentPosition = position
        this.offset = offset
        invalidate()
    }
}