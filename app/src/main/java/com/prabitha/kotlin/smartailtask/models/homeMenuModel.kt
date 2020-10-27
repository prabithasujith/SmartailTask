package com.prabitha.kotlin.smartailtask.models

import com.prabitha.kotlin.smartailtask.R

data class HomeMenuModel(val id: Int, val title: String, val iconLocation: Int)


object HomeMenus {


    private val ids = arrayListOf(
        1, 2, 3, 4, 5, 6
    )
    private val titles = arrayListOf(
        "All Videos", "My Videos", "Quiz time", "Worksheets", "Assignments", "Documents"
    )
    private val imagePath = arrayListOf(
       R.drawable.video1,
        R.drawable.videos,
        R.drawable.quiz,
        R.drawable.tasks,
        R.drawable.assignments,
        R.drawable.docs,


        )

    var list: ArrayList<HomeMenuModel>? = null
        get() {

            if (field != null)      // backing 'field' refers to 'list' property object
                return field

            field = ArrayList()
            for (i in titles.indices) {

                val id = ids[i]
                val title = titles[i]
                val imageLocation = imagePath[i]
                val homeItem = HomeMenuModel(id, title, imageLocation)
                field!!.add(homeItem)
            }

            return field
        }

}