package com.mk.hotfixdemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var msg: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_show_toast.setOnClickListener { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show() }
        tv_jump_two_second.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}
