package com.vedant.paypalapp.sampleRevise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vedant.paypalapp.R
import java.util.ArrayList

class rvAdapter(var context: Context, var arrayList: ArrayList<Eproduct>) : RecyclerView
.Adapter<RecyclerView
.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val productView = LayoutInflater.from(context).inflate(R.layout.rv_row, parent, false)
        return ProductViewHolder(productView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProductViewHolder).initializeUIComponent(
            arrayList[position].id,
            arrayList[position].name,
            arrayList[position].price,
            arrayList[position].productPicture
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    inner class ProductViewHolder(myView: View) : RecyclerView.ViewHolder(myView) {
        var pId = myView.findViewById<TextView>(R.id.txtId)
        var pName = myView.findViewById<TextView>(R.id.txtName)
        var pPrice = myView.findViewById<TextView>(R.id.txtPrice)
        var pImage = myView.findViewById<ImageView>(R.id.rvImageView)

        fun initializeUIComponent(pId: Int, pName: String, pPrice: Int, pImage: Int) {
            this.pId.text = pId.toString()
            this.pName.text = pName
            this.pPrice.text = pPrice.toString()
            this.pImage.setImageResource(pImage)
        }
    }
}
