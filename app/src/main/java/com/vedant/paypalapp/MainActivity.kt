package com.vedant.paypalapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var sharedPrefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnadd.setOnClickListener {
            sharedPrefs = getSharedPreferences("addData", Context.MODE_PRIVATE)
            val myEditor = sharedPrefs?.edit()
            myEditor?.putString("product_name", namepf.text.toString())
            myEditor?.apply()
            Toast.makeText(this@MainActivity,"product saved",Toast.LENGTH_SHORT).show()

        }

        button2.setOnClickListener {
            getProject.text = sharedPrefs?.getString("product_name","nothing")
        }

    }
}
