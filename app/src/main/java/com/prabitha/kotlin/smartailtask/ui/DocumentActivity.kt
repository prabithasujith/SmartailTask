package com.prabitha.kotlin.smartailtask.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.prabitha.kotlin.smartailtask.R
import com.prabitha.kotlin.smartailtask.ui.viewmodels.DocumentViewModel
import kotlinx.android.synthetic.main.activity_document.*
import org.opencv.android.OpenCVLoader


/*
* Activity to scan pictures using camera and remove shadows
* */
class DocumentActivity : AppCompatActivity() {

    private lateinit var documentViewModel: DocumentViewModel

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document)
        supportActionBar?.hide()
        documentViewModel = ViewModelProvider(this).get(DocumentViewModel::class.java)

        //imitialize OpenCv Library
        if (OpenCVLoader.initDebug()) {
            Log.d("log ", " success")
        } else {
            Log.d("log ", " Failure")
        }

        setObservers()
        setListeners()

    }

    //setting listeners for all the buttons
    private fun setListeners() {
        bt_removeShadow.setOnClickListener {
            documentViewModel.removeShadow()
        }

        bt_cancel.setOnClickListener {
            documentViewModel.cancelShadowRemoval()
        }

        bt_done.setOnClickListener{
            this.finish()
        }
    }

    //setting observers for live data
    private fun setObservers() {
        documentViewModel.startCameraCheck.observe(this, {
            if (it) {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                } catch (e: ActivityNotFoundException) {
                    Log.d("error", e.toString())
                    documentViewModel.stopCamera()
                }
            }

        })

        documentViewModel.bitmap.observe(this, {
            if (it != null)
                iv_captured.setImageBitmap(it)

        })


    }

    // handle the captured image from camera
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            try {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                documentViewModel.saveBitmap(imageBitmap)
                documentViewModel.stopCamera()
            } catch (e: Exception) {
                documentViewModel.stopCamera()
            }

        } else {
            this.finish()
        }
    }
}