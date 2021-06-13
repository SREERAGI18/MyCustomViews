package com.example.customfancontroller

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mySnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Activity Created", Snackbar.LENGTH_SHORT)
        mySnackbar.setAction("Let's Go", MyUndoListener())
        mySnackbar.show()
    }

    class MyUndoListener : View.OnClickListener {

        override fun onClick(v: View) {
            val i = Intent(v.context, FanActivity::class.java)
            v.context.startActivity(i)
        }
    }
}