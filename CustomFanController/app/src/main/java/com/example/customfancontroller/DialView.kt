package com.example.customfancontroller

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

private const val SELECTION_COUNT = 4 // Total number of selections.

private var mWidth:Float = 0f // Custom view width.

private var mHeight // Custom view height.
        = 0f
private lateinit var mTextPaint : Paint // For text in the view.

private lateinit var mDialPaint // For dial circle in the view.
        : Paint
private var mRadius // Radius of the circle.
        = 0f
private var mActiveSelection // The active selection.
        = 0

// String buffer for dial labels and float for ComputeXY result.
private val mTempLabel = StringBuffer(8)
private val mTempResult = FloatArray(2)

class DialView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    init {
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        mTextPaint.color = Color.BLACK
        mTextPaint.style = Paint.Style.FILL_AND_STROKE
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextPaint.textSize = 40f

        mDialPaint = Paint(Paint.ANTI_ALIAS_FLAG)

        mDialPaint.color = Color.GRAY

        mActiveSelection = 0

        setOnClickListener(OnClickListener {
            mActiveSelection = (mActiveSelection+1)% SELECTION_COUNT

            // Set dial background color to green if selection is >= 1.
            if (mActiveSelection >= 1) {
                mDialPaint.color = Color.GREEN;
            } else {
                mDialPaint.color = Color.GRAY;
            }
            // Redraw the view.
            invalidate();
        })
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mWidth = w.toFloat()
        mHeight = h.toFloat()

        mRadius = (mWidth.coerceAtMost(mHeight) /2 * 0.8).toFloat()

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Draw the dial.
        canvas?.drawCircle(mWidth/2, mHeight/2, mRadius, mDialPaint)

        // Draw the text labels.
        val labelRadius: Float = mRadius+20
        val label = mTempLabel

        for(i in 0 until SELECTION_COUNT){
            val xyData = computeXYForPosition(i, labelRadius)
            val x = xyData[0]
            val y = xyData[1]

            label.setLength(i)
            label.replace(0,1, i.toString())

            canvas?.drawText(label, 0, label.length, x, y, mTextPaint)
        }

        // Draw the indicator mark.
        val markerRadius = mRadius-35
        val xyData = computeXYForPosition(mActiveSelection, markerRadius)

        val x = xyData[0]
        val y = xyData[1]

        canvas?.drawCircle(x, y, 20F, mTextPaint)
    }

    private fun computeXYForPosition(pos:Int, radius:Float) : FloatArray {
        val result = mTempResult

        val startAngle = Math.PI * (9/8) + Math.PI/8
        val angle = startAngle + (pos * Math.PI/4)

        result[0] = (radius * cos(angle) + mWidth/2).toFloat()
        result[1] = (radius * sin(angle) + mHeight/2).toFloat()

        return result
    }

}
