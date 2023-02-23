package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.followerm.FollowerM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FollowerI {

    @POST("getFollowers")
    suspend fun getFollowerI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<FollowerM>
}