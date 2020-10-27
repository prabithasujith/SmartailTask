package com.prabitha.kotlin.smartailtask.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.prabitha.kotlin.smartailtask.models.VideoModel
import com.prabitha.kotlin.smartailtask.models.Videos

/*
* view model to list all the videos
* */
class VideosViewModel : ViewModel() {
    //fetch the videos
    val videos: List<VideoModel> = Videos.list!!
}