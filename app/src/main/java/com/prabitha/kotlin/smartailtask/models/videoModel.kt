package com.prabitha.kotlin.smartailtask.models

import com.prabitha.kotlin.smartailtask.R

data class VideoModel(val id: Int, val title: String, val subtitle: String, val iconLocation: Int)


object Videos {

    private val titles = arrayListOf(
        "Chapter 1 - A visitor From Distant Land",
        "Chapter 1 - A visitor From Distant Land",
        "Chapter 1 - A visitor From Distant Land",
        "Chapter 1 - A visitor From Distant Land",
        "Chapter 1 - A visitor From Distant Land",
        "Chapter 1 - A visitor From Distant Land"
    )

    private val subtitles = arrayListOf(
        "My House. After their family,the next closest thing to them is their home",
        "My House. After their family,the next closest thing to them is their home",
        "My House. After their family,the next closest thing to them is their home",
        "My House. After their family,the next closest thing to them is their home",
        "My House. After their family,the next closest thing to them is their home",
        "My House. After their family,the next closest thing to them is their home",
    )
    var list: ArrayList<VideoModel>? = null
        get() {

            if (field != null)      // backing 'field' refers to 'list' property object
                return field

            field = ArrayList()
            for (i in titles.indices) {

                val title = titles[i]
                val subtitle= subtitles[i]
                val videoItem = VideoModel(i, title,subtitle, R.drawable.bell)
                field!!.add(videoItem)
            }

            return field
        }

}