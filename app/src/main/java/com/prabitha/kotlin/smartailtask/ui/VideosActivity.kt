package com.prabitha.kotlin.smartailtask.ui

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabitha.kotlin.smartailtask.R
import com.prabitha.kotlin.smartailtask.models.VideoModel
import com.prabitha.kotlin.smartailtask.ui.adapters.VideosRecyclerViewAdapter
import com.prabitha.kotlin.smartailtask.ui.viewmodels.VideosViewModel
import kotlinx.android.synthetic.main.activiy_home.*

class VideosActivity : AppCompatActivity() {

    /*
    * Activity to list all the videos
    * */
    private lateinit var videosViewModel: VideosViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_home)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_tool_bar)
        videosViewModel = ViewModelProvider(this).get(VideosViewModel::class.java)
        showVideos()
    }

    //show videos
    private fun showVideos() {
        homePageRecyclerView.adapter =
            VideosRecyclerViewAdapter(videosViewModel.videos as ArrayList<VideoModel>)
        homePageRecyclerView.setHasFixedSize(true)
        homePageRecyclerView.itemAnimator = DefaultItemAnimator()
        homePageRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}