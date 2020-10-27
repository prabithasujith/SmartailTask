package com.prabitha.kotlin.smartailtask.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.prabitha.kotlin.smartailtask.R
import com.prabitha.kotlin.smartailtask.ui.viewmodels.DocumentViewModel
import com.prabitha.kotlin.smartailtask.ui.viewmodels.VideosViewModel
import kotlinx.android.synthetic.main.activity_document.*
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class DocumentActivity : AppCompatActivity() {

    lateinit var documnetViewModel:DocumentViewModel
    companion object {
        val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)
        documnetViewModel = ViewModelProvider(this).get(DocumentViewModel::class.java)
        if (OpenCVLoader.initDebug()) {
                Toast.makeText(applicationContext, " success", Toast.LENGTH_LONG).show()
            } else {
                Log.d("log ", " Failure")
            }
        documnetViewModel.startCameraCheck.observe(this,{
           if(it)
           {
               val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
               try {
                   startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
               } catch (e: ActivityNotFoundException) {
                   Log.d("error", e.toString())
                   documnetViewModel.stopCamera()
               }
           }

        })

        documnetViewModel.bitmap.observe(this,{
            if(it!=null)
            iv_captured.setImageBitmap(it)
        })

        bt_removeShadow.setOnClickListener {
            documnetViewModel.removeShadow()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            try{
                val imageBitmap = data?.extras?.get("data") as Bitmap
                documnetViewModel.saveBitmap(imageBitmap)
                documnetViewModel.stopCamera()
            }
            catch (e:Exception)
            {
                documnetViewModel.stopCamera()
            }

        }
    }
}