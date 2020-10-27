package com.prabitha.kotlin.smartailtask.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.prabitha.kotlin.smartailtask.models.HomeMenuModel
import com.prabitha.kotlin.smartailtask.models.HomeMenus

class HomeViewModel : ViewModel() {
    private val _navigateToNextScreen = MutableLiveData<Boolean>()
    val navigateToNextScreen: LiveData<Boolean>
        get() {
            return _navigateToNextScreen
        }

     var screenId:Int

     val menus: List<HomeMenuModel> = HomeMenus.list!!
    init {
        _navigateToNextScreen.postValue(false)
        screenId=-1
    }

    fun onMenuClicked(id: Int) {

        _navigateToNextScreen.postValue(true)
        screenId=id
    }

    fun doneNavigation() {
        _navigateToNextScreen.postValue(false)
        screenId=-1
    }
}