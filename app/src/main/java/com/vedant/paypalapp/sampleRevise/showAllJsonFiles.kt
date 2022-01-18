package com.vedant.paypalapp.sampleRevise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_all_json_files.*
import kotlinx.android.synthetic.main.activity_show_json_object.*

class showAllJsonFiles : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_all_json_files)

        textView2.setOnClickListener {
            val productsURL = "http://192.168.104.112/EcommerceApp/present_json_array.php"
            var allProductString  = ""
            val requestQ: RequestQueue = Volley.newRequestQueue(this@showAllJsonFiles)
            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, productsURL,null , { response->

                for(productIndex in 0.until(response.length()))
                {
//                    allProductString  += response.getJSONObject(productIndex).getString("name")+
//                    " - " + response.getJSONObject(productIndex).getString("price")

                    var pn = response.getJSONObject(productIndex).getString("name")
                    var pp = response.getJSONObject(productIndex).getInt("price")
                    allProductString = "$allProductString$pn - $pp\n";

                }
                textView2.text = allProductString
            }, { error->  textView2.text = error.message })
            requestQ.add(jsonArrayRequest)
        }
    }
}
