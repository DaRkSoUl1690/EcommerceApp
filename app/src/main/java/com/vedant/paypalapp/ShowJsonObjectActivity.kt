package com.vedant.paypalapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_json_object.*

class ShowJsonObjectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_json_object)

        button2.setOnClickListener {
          val productURL  = "http://192.168.24.112/EcommerceApp/present_json.php?id=" +
                  edtProductId.text.toString()

            val requestQ: RequestQueue = Volley.newRequestQueue(this@ShowJsonObjectActivity)
             val jsonOR = JsonObjectRequest(
                 Request.Method.GET, productURL,null , { response->
                     textView3.text = response.getString("name")
                     textView4.text = response.getInt("price").toString()
                 }, { error->  textView.text = error.message })
            requestQ.add(jsonOR)
        }
    }
}
