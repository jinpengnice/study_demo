package com.example.mydemo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mydemo.widget.CustomViewGroup

class ScrollerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroller)

        findViewById<Button>(R.id.btn).setOnClickListener {
            startActivity(Intent(this, CustomGroupActivity::class.java))
        }
    }
}