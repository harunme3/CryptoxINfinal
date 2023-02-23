package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.nooffollowing.NoOfFollowingM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NoOfFollowingI {
    @POST("getNoOffFollowing")
    suspend fun getNoOfFollowingI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<NoOfFollowingM>
}