package com.vedant.paypalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_downloading_image.*

class DownloadingImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downloading_image)

        button3.setOnClickListener {

            val imageURL = "http://192.168.104.112/pixabay.jpg"

            Picasso.get().load(imageURL).into(imageView)



        }
    }
}
