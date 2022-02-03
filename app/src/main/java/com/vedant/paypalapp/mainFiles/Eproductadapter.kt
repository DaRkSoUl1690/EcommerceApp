package com.vedant.paypalapp.mainFiles

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vedant.paypalapp.R
import kotlinx.android.synthetic.main.viewholder_product.view.*

class Eproductadapter(
    var context: Context,
    var arrayList: ArrayList<com.vedant.paypalapp.mainFiles.Eproduct>
) : RecyclerView
.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val productView =
            LayoutInflater.from(context).inflate(R.layout.viewholder_product, parent, false)
        return ProductViewHolder(productView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).initializeUIComponent(
            arrayList[position].id, arrayList[position].name,
            arrayList[position].price, arrayList[position].picture
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ProductViewHolder(pView: View) : RecyclerView.ViewHolder(pView) {
        fun initializeUIComponent(id: Int, name: String, price: Int, image: String) {
            itemView.txtIds.text = id.toString()
            itemView.txtNames.text = name
            itemView.txtPrices.text = price.toString()


            var picUrl = "http://192.168.1.100/OnlinestoreApp/osimages/"
            picUrl = picUrl.replace(" ", "%20")

            Picasso.get().load(picUrl + image).into(itemView.imgProduct)

            itemView.add_products.setOnClickListener {

                Person.addtocartid = id
                var amountFragment = AmountFragment()
                var fragmentManager = (itemView.context as Activity).fragmentManager
                amountFragment.show(fragmentManager, "TAG")
            }
        }
    }
}
