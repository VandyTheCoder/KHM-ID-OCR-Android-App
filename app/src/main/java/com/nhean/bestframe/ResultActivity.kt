package com.nhean.bestframe

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
import android.util.Base64
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        supportActionBar!!.title = "Output Result: "

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            val idNumber = bundle.getString("idNumber")
            val birthDate = bundle.getString("dob")
            val gender = bundle.getString("gender")
            val name = bundle.getString("name")

            val image = bundle.getString("image_name")
            val bmp = loadBitmap(baseContext, image.toString())
            image_view.setImageBitmap(bmp)

            val resultOpt = "ID: ${idNumber} \nBirth Date: ${birthDate}\nGender: ${gender}\nName: ${name}"
            result_txt.text = resultOpt
        }
    }

    fun loadBitmap(context: Context, picName:String): Bitmap? {
        var b:Bitmap? = null
        var fis: FileInputStream
        try
        {
            fis = context.openFileInput(picName)
            b = BitmapFactory.decodeStream(fis)
        }
        catch (e: FileNotFoundException) {
            Log.d("FileNotFound", "file not found")
            e.printStackTrace()
        }
        catch (e: IOException) {
            Log.d("FileNotFound", "io exception")
            e.printStackTrace()
        }
        return b
    }
}