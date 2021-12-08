package com.ywj.kotlindemo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import coil.load
import com.ywj.kotlindemo.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    lateinit var flow: Flow<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //测试
        //使用coil；它的全名叫做coroutine image loader,即协程图片加载库
        image.load(R.drawable.pic)
        setupFlow()
        setupClicks()

//        tvTest.setOnClickListener {
        //跳转
//            Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, SecondActivity::class.java)
//            intent.putExtra("data", "1")
//            startActivity(intent)

        //打开一个浏览器
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://www.baidu.com")
//            startActivity(intent)

        //数据返回给上一个Activity
//            val intent = Intent(this, SecondActivity::class.java)
//            startActivityForResult(intent, 1)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == RESULT_OK) {
                val returnData = data?.getStringExtra("data")
                Toast.makeText(this, returnData, Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun setupFlow() {
        flow = flow {
            Log.d(TAG, "Start flow")
            (0..10).forEach {
                // 以500ms的延迟发出从0到10的数字。
                delay(500)
                Log.d(TAG, "Emitting $it")
                emit(it)

            }
        }.map {
            it * it
        }.flowOn(Dispatchers.Default)
    }

    private fun setupClicks() {
        tvTest.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                flow.collect {
                    Log.d(TAG, it.toString())
                }
            }
        }
    }
}