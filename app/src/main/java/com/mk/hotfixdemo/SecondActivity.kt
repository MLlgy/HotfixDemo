package com.mk.hotfixdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {
    var msg: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        msg = "secondActivity"
        tv_jump_two_second.setOnClickListener { Toast.makeText(this, msg?.toString(), Toast.LENGTH_SHORT).show() }
    }
}
