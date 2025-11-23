//package com.example.proxy
//
//import android.app.Application
//import com.example.base.models.ActionResult
//import com.example.base.models.PersonProfile
//import com.example.base.models.ServerConnectState
//import com.example.base.provider.INewsProvider
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.MutableStateFlow
//
//class NewsProvider : INewsProvider {
//
//    private val accountService = ServiceCreator.create<AccountService>()
//
//    override val personProfile = MutableStateFlow(value = PersonProfile.Empty)
//
//    override val serverConnectState = MutableSharedFlow<ServerConnectState>()
//
//    override fun init(application: Application) {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun login(userId: String): ActionResult {
//        val request = LoginRequest(
//            UserID = "qwe",
//        )
//
//        val response = accountService.login(GenerateUserSig.APP_ID, GenerateUserSig.ADMIN_IDENTIFIER, GenerateUserSig.userSign,
//            GenerateUserSig.generateRandom(), requestBody = request);
//
//        if (response.ActionStatus == "OK" && response.ErrorCode == 0) {
//            return ActionResult.Success
//        } else {
//            return ActionResult.Failed(code = response.ErrorCode, reason = response.ErrorInfo)
//        }
//    }
//
//    override suspend fun logout(): ActionResult {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun getPersonProfile(): PersonProfile? {
//        TODO("Not yet implemented")
//    }
//
//    override fun refreshPersonProfile() {
//        TODO("Not yet implemented")
//    }
//
//    override suspend fun updatePersonProfile(
//        faceUrl: String,
//        nickname: String,
//        signature: String
//    ): ActionResult {
//        TODO("Not yet implemented")
//    }
//}