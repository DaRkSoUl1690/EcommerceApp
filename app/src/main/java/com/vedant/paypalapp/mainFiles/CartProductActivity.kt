package com.vedant.paypalapp.mainFiles

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_cart_product.*

class CartProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

        var cartproductUrl = "http://192.168.10.112/OnlinestoreApp/fetch_temp_order" +
                ".php?email=${Person.email}"
        var carproductlist = ArrayList<String>()
        val requestQ = Volley.newRequestQueue(this@CartProductActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET, cartproductUrl, null, { response ->
            for (joIndex in 0.until(response.length())) {
                carproductlist.add(response.getJSONObject(joIndex).getInt("id").toString())
                carproductlist.add(response.getJSONObject(joIndex).getString("name"))
                carproductlist.add(response.getJSONObject(joIndex).getInt("price").toString())
                carproductlist.add(response.getJSONObject(joIndex).getString("email"))
                carproductlist.add(response.getJSONObject(joIndex).getInt("amount").toString())

                var cartAdapter = ArrayAdapter(
                    this@CartProductActivity, android.R.layout
                        .simple_list_item_1, carproductlist
                )
                cartproductlv.adapter = cartAdapter
            }
        }, { })
        requestQ.add(jsonAR)
    }
}
