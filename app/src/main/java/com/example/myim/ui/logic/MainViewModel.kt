package com.example.myim.ui.logic

import androidx.lifecycle.viewModelScope
import com.example.myim.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    init {
        viewModelScope.launch {
            launch {
                requestData()
            }
        }
    }

    private fun requestData() {
        MyIM.accountProvider.refreshPersonProfile()
    }
}