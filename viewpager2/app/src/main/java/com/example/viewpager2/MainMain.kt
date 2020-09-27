package com.example.viewpager2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main_main.*

class MainMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_main)
    btn.setOnClickListener {
        intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
    }


}