package com.peiflow.ruedasrarasapp.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.peiflow.ruedasrarasapp.R
import java.io.InputStream
import java.lang.Exception
import java.net.URL

class ImageUtils : AsyncTask<String, Void, Bitmap> {

    private var imageView: ImageView? = null
    private var imageBtn: ImageButton? = null

    constructor(imageView: ImageView) : super() {
        this.imageView = imageView
    }

    constructor(imageBtn: ImageButton) : super() {
        this.imageBtn = imageBtn
    }

    override fun doInBackground(vararg params: String?): Bitmap {
        //TODO: arreglar esto para que cuando no haya internet no pete
        lateinit var bimage: Bitmap 
        val imageUrl: String? = params[0]

        try {

        val inStr:InputStream = URL(imageUrl).openStream()
            bimage = BitmapFactory.decodeStream(inStr)

        } catch (e: Exception) {
            Log.e("Error message", e.message)
            e.printStackTrace()
        }
        return bimage
    }

    override fun onPostExecute(result: Bitmap) {
        if(imageView != null)
        {
            imageView?.setImageBitmap(result)
        }else if(imageBtn != null)
        {
            imageBtn?.setImageBitmap(result)
        }
    }
}