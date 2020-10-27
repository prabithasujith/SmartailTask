package com.prabitha.kotlin.smartailtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.prabitha.kotlin.smartailtask.models.VideoModel
import com.prabitha.kotlin.smartailtask.models.Videos

class VideosViewModel : ViewModel() {
    val videos: List<VideoModel> = Videos.list!!
}