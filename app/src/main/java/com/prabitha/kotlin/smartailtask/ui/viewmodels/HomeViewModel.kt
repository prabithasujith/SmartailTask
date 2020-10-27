package com.prabitha.kotlin.smartailtask.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabitha.kotlin.smartailtask.models.HomeMenuModel
import com.prabitha.kotlin.smartailtask.models.HomeMenus

/*
* View model for the home page
* */
class HomeViewModel : ViewModel() {
    //variable to check if to navigate or not
    private val _navigateToNextScreen = MutableLiveData<Boolean>()
    val navigateToNextScreen: LiveData<Boolean>
        get() {
            return _navigateToNextScreen
        }

    //screen id 0 - All videos 5- Document scanning -1 - no option selected
    var screenId: Int

    //fetch the menus
    val menus: List<HomeMenuModel> = HomeMenus.list!!

    init {
        _navigateToNextScreen.postValue(false)
        screenId = -1
    }

    // called when home menu is clicked
    fun onMenuClicked(id: Int) {

        _navigateToNextScreen.postValue(true)
        screenId = id
    }

    //on done navigation set the Livedata to default value
    fun doneNavigation() {
        _navigateToNextScreen.postValue(false)
        screenId = -1
    }
}