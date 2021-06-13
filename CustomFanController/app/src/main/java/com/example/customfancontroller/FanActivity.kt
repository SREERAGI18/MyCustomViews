package com.example.customfancontroller

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fan.*

class FanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan)

        showGif(imageView)
    }

    private fun showGif(view: View?) {
        Glide.with(this).load("http://gifb.in/EBsV").into(view as ImageView)
    }
}