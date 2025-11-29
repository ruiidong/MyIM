package com.example.myim.ui.new.logic

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.myim.provider.ContextProvider
import com.example.myim.ui.base.BaseViewModel
import com.example.proxy.AppDatabase
import com.example.proxy.NewProvider
import com.example.proxy.NewService
import com.example.proxy.ServiceCreator

class NewViewModel : BaseViewModel() {

    private val newProvider : NewProvider = NewProvider(AppDatabase.getDatabase(ContextProvider.context),
        ServiceCreator.create<NewService>())

    val pager = newProvider.getAllNews().flow.cachedIn(viewModelScope)
}