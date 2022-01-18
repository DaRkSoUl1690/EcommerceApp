package com.vedant.paypalapp.mainFiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.Response
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
            if(state == State.LOGIN ) {
                state = State.SIGNUP
                bsignup.text = "Login"
                blogin.text = "Sign up"
                editconfirmpass.visibility = View.VISIBLE;
                username.visibility = View.VISIBLE

            }
            else {
                state = State.LOGIN
                blogin.text = "Login"
                bsignup.text = "Sign up"
                editconfirmpass.visibility = View.GONE;
                username.visibility = View.GONE

            }
        }

        blogin.setOnClickListener {
            if(state == State.LOGIN) {
                // Login function
            }
            else
            {

                if(edtpass.text.toString() == editconfirmpass.text.toString()){
                   val signUpUrl = "http://192.168.163.112//OnlinestoreApp/join_new_user" +
                           ".php?email="+edtemail.text.toString()+"&user="+username.text.toString()+
                           "&pass="+edtpass.text.toString()

                    val requestQ = Volley.newRequestQueue(this@Login_Activity)
                    val stringRequest = StringRequest(Request.Method.GET,signUpUrl, {response->
                       if(response.equals("user with this same email exists"))
                       {
                           val dialog = AlertDialog.Builder(this)
                           dialog.setTitle("Alert")
                           dialog.setMessage(response)
                           dialog.create().show()
                       }
                        else
                       {
                           val dialog = AlertDialog.Builder(this)
                           dialog.setTitle("Alert")
                           dialog.setMessage(response)
                           dialog.create().show()
                       }
                    }, {  error-> val dialog = AlertDialog.Builder(this)
                        dialog.setTitle("Alert")
                        dialog.setMessage(error.message)
                        dialog.create().show()})

                    requestQ.add(stringRequest)
                }
                else
                {
                    alertMessage();
                }
            }
        }
    }


private fun alertMessage(){
    val dialog = AlertDialog.Builder(this)
    dialog.setTitle("Alert")
    dialog.setMessage("Password Mismatch")
    dialog.create().show()
}

}
