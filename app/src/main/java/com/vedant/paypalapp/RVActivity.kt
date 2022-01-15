package com.vedant.paypalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_rvactivity.*

class RVActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rvactivity)

       val myProductList = arrayListOf<Eproduct>()
        myProductList.add(Eproduct(0,"iphone",2000,R.drawable.iphone))
        myProductList.add(Eproduct(1,"iphone",2000,R.drawable.iphone))
        myProductList.add(Eproduct(2,"iphone",2000,R.drawable.iphone))
        myProductList.add(Eproduct(3,"iphone",2000,R.drawable.iphone))
        myProductList.add(Eproduct(4,"iphone",2000,R.drawable.iphone))
        myProductList.add(Eproduct(5,"iphone",2000,R.drawable.iphone))
        myProductList.add(Eproduct(6,"iphone",2000,R.drawable.iphone))
        myProductList.add(Eproduct(7,"iphone",2000,R.drawable.iphone))


        val adapter = rvAdapter(this@RVActivity,myProductList)
        recyclerView.layoutManager = LinearLayoutManager(this@RVActivity)
        recyclerView.adapter  = adapter
    }
}
