package com.vedant.paypalapp.mainFiles

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_cart_product.*

class CartProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_product)

        var cartproductUrl = "http://192.168.56.112/OnlinestoreApp/fetch_temp_order" +
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.continueShoppingItem) {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.verifyItem) {

        }
        if (item.itemId == R.id.declineorder) {
            val deleteUrl = "http://192.168.56.112/OnlinestoreApp/decline_order.php?email=" +
                    Person.email

            val requestQ = Volley.newRequestQueue(this@CartProductActivity)
            val jsonAR = StringRequest(Request.Method.GET, deleteUrl, { response ->
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
            }, { })
            requestQ.add(jsonAR)
        }
        if (item.itemId == R.id.verifyItem) {
            val verifyUrl = "http://192.168.56.112/OnlinestoreApp/verify_orders.php?email=" +
                    Person.email

            val requestQ = Volley.newRequestQueue(this@CartProductActivity)
            val jsonAR = StringRequest(Request.Method.GET, verifyUrl, { response ->
                val intent = Intent(this, FinalizeActivity::class.java)
                intent.putExtra("LATEST_INVOICE_NUMBER", response)
                startActivity(intent)
            }, { })
            requestQ.add(jsonAR)
        }
        return super.onOptionsItemSelected(item)
    }
}
