package com.vedant.paypalapp.mainFiles

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.activity_login.*

class Login_Activity : AppCompatActivity() {


    internal enum class State {
        SIGNUP, LOGIN
    }

    private var state: State? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editconfirmpass.visibility = View.GONE;
        username.visibility = View.GONE
        state = State.LOGIN;
        bsignup.setOnClickListener {
            if (state == State.LOGIN) {
                state = State.SIGNUP
                bsignup.text = "Login"
                blogin.text = "Sign up"
                editconfirmpass.visibility = View.VISIBLE;
                username.visibility = View.VISIBLE

            } else {
                state = State.LOGIN
                blogin.text = "Login"
                bsignup.text = "Sign up"
                editconfirmpass.visibility = View.GONE;
                username.visibility = View.GONE

            }
        }

        blogin.setOnClickListener {
            if (state == State.LOGIN) {
                login()
            } else {
                signUp()
            }
        }
    }

    fun signUp() {

        if (edtpass.text.toString() == editconfirmpass.text.toString()) {
            val signUpUrl =
                "http://192.168.1.101/OnlinestoreApp/join_new_user.php?email=" + edtemail.text.toString() + "&user=" + username.text.toString() +
                        "&pass=" + edtpass.text.toString()
//            Toast.makeText(this@Login_Activity, signUpUrl, Toast.LENGTH_SHORT).show()

            val requestQ = Volley.newRequestQueue(this@Login_Activity)
            val stringRequest = StringRequest(Request.Method.GET, signUpUrl, { response ->
//            Toast.makeText(this@Login_Activity, response, Toast.LENGTH_SHORT).show()
                if (response == "user with this same email exists") {
                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Alert")
                    dialog.setMessage(response)
                    dialog.create().show()
                } else {
                    Person.email = edtemail.text.toString()
                    val intent = Intent(this@Login_Activity, HomeScreen::class.java)
                    startActivity(intent)
                    val dialog = AlertDialog.Builder(this)
                    dialog.setTitle("Alert")
                    dialog.setMessage(response)
                    dialog.create().show()
                }
            }, { error ->
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Alert")
                dialog.setMessage("error")
                dialog.create().show()
            })

            requestQ.add(stringRequest)
        } else {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Alert")
            dialog.setMessage("Password Mismatch")
            dialog.create().show()
        }
    }

    fun login() {
        val loginUrl = "http://192.168.1.101/OnlinestoreApp/login_app_user.php?email=" +
                edtemail.text.toString() + "&pass=" + edtpass.text.toString()
//        Toast.makeText(this@Login_Activity, loginUrl, Toast.LENGTH_SHORT).show()
        val requestQ = Volley.newRequestQueue(this@Login_Activity)
        val stringRequest = StringRequest(Request.Method.GET, loginUrl, { response ->
//            Toast.makeText(this@Login_Activity, response, Toast.LENGTH_SHORT).show()
            if (response.equals("user with this same email exists")) {
                Person.email = edtemail.text.toString()
                val intent = Intent(this@Login_Activity, HomeScreen::class.java)
                startActivity(intent)
//                val dialog = AlertDialog.Builder(this)
//                dialog.setTitle("Alert")
//                dialog.setMessage(response)
//                dialog.create().show()
            } else {
                Toast.makeText(this@Login_Activity, response, Toast.LENGTH_SHORT).show()
            }
        }, { error ->

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Alert")
            dialog.setMessage("error")
            dialog.create().show()
        })

        requestQ.add(stringRequest)

    }
}
