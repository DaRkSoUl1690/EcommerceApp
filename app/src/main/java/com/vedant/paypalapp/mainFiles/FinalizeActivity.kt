package com.vedant.paypalapp.mainFiles

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.PayPalPayment
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_finalize.*
import java.math.BigDecimal

class FinalizeActivity : AppCompatActivity() {

    private var ttlPrice: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize)

        val ttlPriceUrl = "http://192.168.35.112/OnlinestoreApp/calculate_total_price" +
                ".php?invoice_num=${intent.getStringExtra("LATEST_INVOICE_NUMBER")}"

        val requestQ = Volley.newRequestQueue(this@FinalizeActivity)
        val jsonAR = StringRequest(Request.Method.GET, ttlPriceUrl, { response ->
            btn_payment.text = "Pay $response via PayPal"
//            ttlPrice = response.toLong()
        }, { })
        requestQ.add(jsonAR)

        val paypalConfig: PayPalConfiguration = PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(MyPaypal.clientID)

        val ppService = Intent(this@FinalizeActivity, PayPalService::class.java)
        ppService.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
        startService(ppService)

        btn_payment.setOnClickListener {
            val ppProcessing = PayPalPayment(
                BigDecimal.valueOf(ttlPrice), "USD", "Online Store " +
                        "Kotlin!", PayPalPayment.PAYMENT_INTENT_SALE
            )

            val paypalPaymentIntent = Intent(this, PayPalPayment::class.java)
            paypalPaymentIntent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, paypalConfig)
            paypalPaymentIntent.putExtra(PaymentActivity.EXTRA_PAYMENT, ppProcessing)
//            startService(paypalPaymentIntent)
            startActivity(paypalPaymentIntent)
        }

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 1000) {
//            if (resultCode == Activity.RESULT_OK) {
//                val intent = Intent(this@FinalizeActivity, ThankYou::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this@FinalizeActivity, "sorry", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()

        stopService(Intent(this, PayPalService::class.java))
    }
}
