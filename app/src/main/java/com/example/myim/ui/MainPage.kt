package com.example.myim.ui

import androidx.compose.runtime.Composable
import com.example.myim.ui.logic.MainViewModel
import com.example.myim.ui.person.PersonProfilePage
import com.example.myim.ui.person.logic.PersonProfileViewModel

@Composable
fun MainPage(
    mainViewModel: MainViewModel,
    personProfileViewModel: PersonProfileViewModel
) {
    PersonProfilePage(pageViewState = personProfileViewModel.pageViewState)
}