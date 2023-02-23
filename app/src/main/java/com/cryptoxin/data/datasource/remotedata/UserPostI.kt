package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.userpostm.UserPostM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserPostI {

    @POST("getPost")
    suspend fun getUserPostI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<UserPostM>
}