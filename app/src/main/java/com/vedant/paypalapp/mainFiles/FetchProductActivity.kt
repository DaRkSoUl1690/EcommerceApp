package com.vedant.paypalapp.mainFiles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_fetch_product.*

class FetchProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetch_product)

        val selectedProduct = intent.getStringExtra("BRAND")
        val productList = ArrayList<Eproduct>()
        val productUrl =
            "http:///192.168.35.112/OnlinestoreApp/fetch_eproducts.php?brand=$selectedProduct"
        val requestQ = Volley.newRequestQueue(this@FetchProductActivity)
        val jsonAR = JsonArrayRequest(Request.Method.GET, productUrl, null, { response ->
            for (productJO in 0.until(response.length())) {
                productList.add(
                    Eproduct(
                        response.getJSONObject(productJO).getInt("id"),
                        response.getJSONObject(productJO).getString("name"),
                        response.getJSONObject(productJO).getInt("price"),
                        response.getJSONObject(productJO).getString("image")
                    )
                )
            }
            val pAdapter = Eproductadapter(this@FetchProductActivity, productList)
            productrv.layoutManager = LinearLayoutManager(this@FetchProductActivity)
            productrv.adapter = pAdapter
        }, { })
        requestQ.add(jsonAR)
    }
}
