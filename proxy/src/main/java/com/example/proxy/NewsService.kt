//package com.example.proxy
//
//import retrofit2.http.Body
//import retrofit2.http.POST
//import retrofit2.http.Query
//
//interface AccountService {
//
//    @POST("v4/im_open_login_svc/account_import")
//    suspend fun login(
//        @Query("sdkappid") sdkAppId: Int,
//        @Query("identifier") identifier: String,
//        @Query("usersig") userSig: String,
//        @Query("random") random: Int,
//        @Query("contenttype") contentType: String = "json",
//        @Body requestBody: LoginRequest
//    ): LoginResponse
//}
//
//data class LoginRequest(
//
//    val UserID: String,
//
//    val Nick: String? = null,
//
//    val FaceUrl: String? = null
//)
//
//data class LoginResponse(
//    /**
//     * 请求处理的结果：OK 表示处理成功，FAIL 表示失败
//     */
//    val ActionStatus: String,
//
//    /**
//     * 错误信息
//     */
//    val ErrorInfo: String,
//
//    /**
//     * 错误码：0表示成功，非0表示失败
//     */
//    val ErrorCode: Int
//)