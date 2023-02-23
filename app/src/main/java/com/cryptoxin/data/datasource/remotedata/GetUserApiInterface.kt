package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.GetUserModelBody
import com.cryptoxin.data.models.getuser.GetUserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GetUserApiInterface {
    @POST("getUser")
    suspend fun getUserInterface(@Body getUserModelBody: GetUserModelBody): Response<GetUserModel>
}