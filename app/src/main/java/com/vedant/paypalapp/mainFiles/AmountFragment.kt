package com.vedant.paypalapp.mainFiles

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.fragment_amount.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AmountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AmountFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentView = inflater.inflate(R.layout.fragment_amount, container, false)

        var editEnterAmount = fragmentView.findViewById<EditText>(R.id.edtAmmount)
        var addtochart = fragmentView.findViewById<Button>(R.id.addtochart)

        addtochart.setOnClickListener {
            var tempUrl = "http://192.168.10.112/OnlinestoreApp/insert_temp_order" +
                    ".php?email=${Person.email}&product_id=${Person.addtocartid}&amount=" +
                    edtAmmount.text.toString()

            val requestQ = Volley.newRequestQueue(activity)
            val jsonAR = StringRequest(Request.Method.GET, tempUrl, { response ->

                var intent = Intent(activity, CartProductActivity::class.java)
                startActivity(intent)
            }, { })
            requestQ.add(jsonAR)

        }
        return fragmentView

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AmountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AmountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
