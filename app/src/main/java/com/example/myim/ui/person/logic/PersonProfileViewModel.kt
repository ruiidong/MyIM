package com.example.myim.ui.person.logic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.base.models.PersonProfile
import com.example.myim.ui.base.BaseViewModel
import com.example.myim.ui.logic.MyIM
import com.example.myim.ui.preview.PreviewImageActivity
import kotlinx.coroutines.launch

class PersonProfileViewModel : BaseViewModel() {

    var pageViewState by mutableStateOf(
        value = PersonProfilePageViewState(
            personProfile = PersonProfile.Empty,
            previewImage = ::previewImage
        )
    )
        private set

    init {
        viewModelScope.launch {
            MyIM.accountProvider.personProfile.collect {
                pageViewState = pageViewState.copy(personProfile = it)
            }
        }
    }

    private fun previewImage(imageUrl: String) {
        if (imageUrl.isNotBlank()) {
            PreviewImageActivity.navTo(context = context, imageUri = imageUrl)
        }
    }

}