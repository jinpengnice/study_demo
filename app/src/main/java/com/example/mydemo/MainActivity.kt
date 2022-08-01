package com.example.mydemo

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val llParent = findViewById<LinearLayout>(R.id.ll_parent)
        val btnChild = findViewById<Button>(R.id.btn_child)
//
//
//        llParent.setOnClickListener{
//            llParent.scrollTo(100, 0)
//        }
//
        btnChild.setOnClickListener {
//            Toast.makeText(this, "测试位置", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, ScrollerActivity::class.java))
        }

    }

//    var mLastX = 0
//    var mLastY = 0
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        event?.let {
//            val x = event.x
//            val y = event.y
//            val decorView = window.decorView
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> {
//                    mLastX = x.toInt()
//                    mLastY = y.toInt()
//                }
//                MotionEvent.ACTION_MOVE -> {
//                    val dx = mLastX - x
//                    val dy = mLastY - y
//                    val oldScrollX = decorView.scrollX
//                    val oldScrollY = decorView.scrollY
//                    val scrollX = oldScrollX - dx
//                    val scrollY = oldScrollY - dy
//                    decorView.scrollTo(scrollX.toInt(), scrollY.toInt())
//                    mLastX = x.toInt()
//                    mLastY = y.toInt()
//                }
//            }
//        }
//        return true
//    }
}