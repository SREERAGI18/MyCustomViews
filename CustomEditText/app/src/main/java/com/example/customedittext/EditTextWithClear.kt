package com.example.customedittext

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class EditTextWithClear : AppCompatEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):super(context, attrs, defStyleAttr){
        init()
    }

    private lateinit var mClearButtonImage:Drawable
    
    private fun init() {
        mClearButtonImage =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_clear_opaque_24, null)!!

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showClearButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        setOnTouchListener(object : OnTouchListener {

            @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                if((compoundDrawablesRelative[2] != null)) run {
                    var clearButtonStart: Float
                    var clearButtonEnd:Float

                    var isClearButtonClicked: Boolean = false

                    if(layoutDirection == LAYOUT_DIRECTION_RTL){
                        clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()

                        if (event != null) {
                            if(event.x < clearButtonEnd){
                                isClearButtonClicked = true
                            }
                        }
                    }else{
                        clearButtonStart = ((width-paddingEnd) - mClearButtonImage.intrinsicWidth).toFloat()

                        if (event != null) {
                            if(event.x > clearButtonStart){
                                isClearButtonClicked = true
                            }
                        }
                    }

                    if(isClearButtonClicked){
                        if (event != null) {
                            if(event.action == MotionEvent.ACTION_DOWN){
                                mClearButtonImage = ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_baseline_clear_opaque_24,
                                        null
                                )!!
                            }

                            showClearButton()
                        }

                        if (event != null) {
                            if(event.action == MotionEvent.ACTION_UP){
                                mClearButtonImage = ResourcesCompat.getDrawable(
                                        resources,
                                        R.drawable.ic_baseline_clear_24,
                                        null
                                )!!

                                text?.clear()
                                hideClearButton()
                                return true
                            }

                            showClearButton()
                        }
                    }

                }

                return false;
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                mClearButtonImage,
                null
        )
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                null,
                null
        )
    }
}