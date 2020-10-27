package com.prabitha.kotlin.smartailtask.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

/*
document scanning page view model
* */
class DocumentViewModel : ViewModel() {

    //holds original bitmap from camera
    private lateinit var originalbitmap:Bitmap
    //holds either the original/ processed bitmap
    private var _bitmap = MutableLiveData<Bitmap>()

    val bitmap: LiveData<Bitmap>
        get() = _bitmap
    //start/stop camera
    val startCameraCheck = MutableLiveData<Boolean>()

    init {
        _bitmap.postValue(null)
        startCameraCheck.postValue(true)
    }

    //stop the camera after the user clicks the picture
    fun stopCamera() {
        startCameraCheck.postValue(false)
    }

    //saving the bitmap temporarily in the local variable
    fun saveBitmap(capturedBitmap: Bitmap) {
        originalbitmap=capturedBitmap.copy(capturedBitmap.config,true)
        _bitmap.postValue(capturedBitmap)
    }

    //process the bitmap to remove the shadows on it
    fun removeShadow() {
        val mat = Mat()
        val copyBitmap = _bitmap.value
        Utils.bitmapToMat(copyBitmap, mat)
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY)

        Imgproc.adaptiveThreshold(mat,mat,255.0,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,11,
            2.0
        )
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB)
        Utils.matToBitmap(mat,copyBitmap)
        _bitmap.postValue(copyBitmap)

    }

    //remove the processing and set back to original image
    fun cancelShadowRemoval(){
        _bitmap.postValue(originalbitmap)
    }

}