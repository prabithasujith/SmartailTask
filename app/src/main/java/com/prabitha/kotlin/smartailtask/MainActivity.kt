package com.prabitha.kotlin.smartailtask

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.prabitha.kotlin.smartailtask.models.HomeMenuModel
import com.prabitha.kotlin.smartailtask.ui.DocumentActivity
import com.prabitha.kotlin.smartailtask.ui.VideosActivity
import com.prabitha.kotlin.smartailtask.ui.adapters.HomePageRecyclerAdapter
import com.prabitha.kotlin.smartailtask.ui.viewmodels.HomeViewModel
import com.prabitha.kotlin.smartailtask.utils.checkSelfPermissionCompat
import com.prabitha.kotlin.smartailtask.utils.requestPermissionsCompat
import com.prabitha.kotlin.smartailtask.utils.showSnackbar
import com.prabitha.kotlin.smartailtask.utils.shouldShowRequestPermissionRationaleCompat
import kotlinx.android.synthetic.main.activiy_home.*


/*
* Activity launched on opening the app
* It shows all the menus on the home page
* */
const val PERMISSION_REQUEST_CAMERA = 0
class MainActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback  {


    private lateinit var homeMenuViewModel: HomeViewModel
    private lateinit var layout: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_home)
        layout=findViewById(R.id.layout)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.custom_tool_bar)
        homeMenuViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        showMenus()
        setObservers()


    }

    //show all the menus
    private fun showMenus() {
        homePageRecyclerView.adapter = HomePageRecyclerAdapter(
            homeMenuViewModel.menus as ArrayList<HomeMenuModel>,
            homeMenuViewModel
        )

    }

    //set listeners
    private fun setObservers() {
        homeMenuViewModel.navigateToNextScreen.observe(this, {
            if (it) {
                //start the video activity on click of all videos
                if (homeMenuViewModel.screenId == 0) {
                   val intent= Intent(this, VideosActivity::class.java)
                    startActivity(intent)
                } else {
                    //start document activity
                    showDcumentActivity()
                }


            }

        })

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            // Request for camera permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                layout.showSnackbar(R.string.camera_permission_granted, Snackbar.LENGTH_SHORT)
                startCamera()
            } else {
                // Permission request was denied.
                layout.showSnackbar(R.string.camera_permission_denied, Snackbar.LENGTH_SHORT)
            }
        }
    }

    private fun showDcumentActivity() {
        // Check if the Camera permission has been granted
        if (checkSelfPermissionCompat(Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            layout.showSnackbar(R.string.camera_permission_available, Snackbar.LENGTH_SHORT)
            startCamera()
        } else {
            requestCameraPermission()
        }
    }

    /**
     * Requests the [android.Manifest.permission.CAMERA] permission.
     * If an additional rationale should be displayed, the user has to launch the request from
     * a SnackBar that includes additional information.
     */
    private fun requestCameraPermission() {
        // Permission has not been granted and must be requested.
        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with a button to request the missing permission.

            layout.showSnackbar(R.string.camera_access_required,
                Snackbar.LENGTH_INDEFINITE, R.string.ok) {
                requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA),
                    PERMISSION_REQUEST_CAMERA)
            }

        } else {
            layout.showSnackbar(R.string.camera_permission_not_available, Snackbar.LENGTH_SHORT)

            // Request the permission. The result will be received in onRequestPermissionResult().
            requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
        }
    }

    private fun startCamera() {
        val intent = Intent(this, DocumentActivity::class.java)
        startActivity(intent)
    }
}



