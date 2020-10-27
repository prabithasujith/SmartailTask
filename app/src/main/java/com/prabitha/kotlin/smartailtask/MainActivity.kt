package com.prabitha.kotlin.smartailtask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.prabitha.kotlin.smartailtask.models.HomeMenuModel
import com.prabitha.kotlin.smartailtask.ui.DocumentActivity
import com.prabitha.kotlin.smartailtask.ui.VideosActivity
import com.prabitha.kotlin.smartailtask.ui.adapters.HomePageRecyclerAdapter
import com.prabitha.kotlin.smartailtask.ui.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.activiy_home.*


class MainActivity : AppCompatActivity() {


    lateinit var homeMenuViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_home)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_tool_bar)
        homeMenuViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        showMenus()
        setListeners()

    }

    private fun showMenus() {
        homePageRecyclerView.adapter = HomePageRecyclerAdapter(
            homeMenuViewModel.menus as ArrayList<HomeMenuModel>,
            homeMenuViewModel
        )

    }

    private fun setListeners() {
        homeMenuViewModel.navigateToNextScreen.observe(this, {
            if (it) {
                var intent: Intent
                //start the video activity on click of all videos
                if (homeMenuViewModel.screenId == 0) {
                    intent = Intent(this, VideosActivity::class.java)

                } else {
                    //start document activity
                    intent = Intent(this, DocumentActivity::class.java)

                }
                startActivity(intent)
                homeMenuViewModel.doneNavigation()
            }
        })
    }


}

