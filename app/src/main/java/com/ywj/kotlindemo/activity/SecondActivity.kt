package com.ywj.kotlindemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ywj.kotlindemo.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        val intent = intent.getStringExtra("data")
//        Toast.makeText(this, intent, Toast.LENGTH_SHORT).show()

        btnClick.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data", "hello")
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    /**
     * 返回按钮
     */
    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data", "hello")
        setResult(RESULT_OK, intent)
        finish()
    }
}