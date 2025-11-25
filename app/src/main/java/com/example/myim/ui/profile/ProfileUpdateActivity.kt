package com.example.myim.ui.profile

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myim.ui.base.BaseActivity
import com.example.myim.ui.profile.logic.ProfileUpdatePageViewStata
import com.example.myim.ui.profile.logic.ProfileUpdateViewModel
import com.example.myim.ui.widgets.CommonButton
import com.example.myim.ui.widgets.CommonOutlinedTextField
import com.example.myim.ui.widgets.ProfilePanel
import com.example.myim.utils.randomImage

class ProfileUpdateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val profileUpdateViewModel = viewModel<ProfileUpdateViewModel>()
            ProfileUpdatePage(pageViewStata = profileUpdateViewModel.profileUpdatePageViewStata)
        }
    }

}

@Composable
private fun ProfileUpdatePage(pageViewStata: ProfileUpdatePageViewStata) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        contentWindowInsets = WindowInsets.navigationBars
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
                .verticalScroll(state = rememberScrollState())
        ) {
            val personProfile = pageViewStata.personProfile
            if (personProfile != null) {
                ProfilePanel(
                    title = personProfile.nickname,
                    subtitle = personProfile.signature,
                    introduction = "",
                    avatarUrl = personProfile.faceUrl
                ) {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(space = 16.dp)
                    ) {
                        CommonOutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            value = personProfile.nickname,
                            onValueChange = {
                                if (it.length > 16) {
                                    return@CommonOutlinedTextField
                                }
                                pageViewStata.onNicknameChanged(it)
                            },
                            label = "nickname"
                        )
                        CommonOutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            value = personProfile.signature,
                            onValueChange = {
                                if (it.length > 40) {
                                    return@CommonOutlinedTextField
                                }
                                pageViewStata.onSignatureChanged(it)
                            },
                            label = "signature"
                        )
                        CommonButton(text = "随机头像") {
                            pageViewStata.onAvatarUrlChanged(randomImage())
                        }
                        CommonButton(text = "确认修改") {
                            pageViewStata.confirmUpdate()
                        }
                    }
                }
            }
        }
    }
}