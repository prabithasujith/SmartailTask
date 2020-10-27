package com.prabitha.kotlin.smartailtask.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.opencv.android.Utils
import org.opencv.core.Mat
import org.opencv.imgproc.Imgproc

class DocumentViewModel : ViewModel() {

    private var _bitmap = MutableLiveData<Bitmap>()

    init {
        _bitmap.postValue(null)
    }

    val bitmap: LiveData<Bitmap>
        get() = _bitmap
    val startCameraCheck = MutableLiveData<Boolean>()

    init {
        startCameraCheck.postValue(true)
    }

    fun stopCamera() {
        startCameraCheck.postValue(false)
    }

    fun saveBitmap(cbitmap: Bitmap) {
        _bitmap.postValue(cbitmap)
    }

    fun removeShadow() {
        val mat = Mat()
        val _copyBitmap = _bitmap.value
        Utils.bitmapToMat(_copyBitmap, mat)
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY)

        Imgproc.adaptiveThreshold(mat,mat,255.0,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,11,
            2.0
        )
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB)
        Utils.matToBitmap(mat,_copyBitmap)
        _bitmap.postValue(_copyBitmap)

    }

}