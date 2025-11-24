package com.example.myim.ui.person.logic

import androidx.compose.runtime.Stable
import com.example.base.models.PersonProfile

@Stable
data class PersonProfilePageViewState(
    val personProfile: PersonProfile,
    val previewImage: (String) -> Unit
)