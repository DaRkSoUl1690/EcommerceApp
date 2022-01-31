package com.vedant.paypalapp.mainFiles

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vedant.paypalapp.sampleRevise.Eproduct
import kotlinx.android.synthetic.main.rv_row.view.txtId
import kotlinx.android.synthetic.main.rv_row.view.txtName
import kotlinx.android.synthetic.main.rv_row.view.txtPrice
import kotlinx.android.synthetic.main.viewholder_product.view.*

class Eproductadapter(var context: Context, var arrayList: ArrayList<Eproduct>) : RecyclerView
.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ProductViewHolder(pView: View) : RecyclerView.ViewHolder(pView) {
        fun initializeUIComponent(id: Int, name: String, price: Int, image: String) {
            itemView.txtId.text = id.toString()
            itemView.txtName.text = name
            itemView.txtPrice.text = price.toString()

            var picUrl = "http://192.168.117.112/OnlinestoreApp/osimages/"
            picUrl = picUrl.replace(" ", "%20")

            Picasso.get().load(picUrl + image).into(itemView.imgProduct)

        }
    }
}
