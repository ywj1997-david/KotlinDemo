package com.ywj.kotlindemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ywj.kotlindemo.R

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "mainactivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}