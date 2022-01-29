package com.vedant.paypalapp.mainFiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val brandUrl = "http://192.168.1.101/OnlinestoreApp/fetch_brands.php"
        val brandList = ArrayList<String>()
        val requestQ = Volley.newRequestQueue(this@HomeScreen)
        val jsonAR = JsonArrayRequest(Request.Method.GET, brandUrl, null, { response ->
            for (jsonObject in 0.until(response.length())) {
                brandList.add(response.getJSONObject(jsonObject).getString("brand"))
            }
            val brandListAdapter =
                ArrayAdapter(this@HomeScreen,R.layout.brand_text_view, brandList);
            brand_listview.adapter = brandListAdapter


        }, { });

        requestQ.add(jsonAR)
    }

}
