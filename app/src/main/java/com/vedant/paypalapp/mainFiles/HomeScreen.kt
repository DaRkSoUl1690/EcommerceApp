package com.vedant.paypalapp.mainFiles

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val brandUrl = "http:///192.168.56.112/OnlinestoreApp/fetch_brands.php"
        val brandList = ArrayList<String>()
        val requestQ = Volley.newRequestQueue(this@HomeScreen)
        val jsonAR = JsonArrayRequest(Request.Method.GET, brandUrl, null, { response ->
            for (jsonObject in 0.until(response.length())) {
                brandList.add(response.getJSONObject(jsonObject).getString("brand"))
            }
            val brandListAdapter =
                ArrayAdapter(this@HomeScreen, R.layout.brand_text_view, brandList)
            brand_listview.adapter = brandListAdapter


        }, { })

        requestQ.add(jsonAR)

        brand_listview.setOnItemClickListener { parent, view, position, id ->

            val tappedBrand = brandList[position]

            val intent = Intent(this@HomeScreen, FetchProductActivity::class.java)
            intent.putExtra("BRAND", tappedBrand)
            startActivity(intent)

        }
    }

}
