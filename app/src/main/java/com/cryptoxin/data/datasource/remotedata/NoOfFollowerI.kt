package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.nooffollower.NoOfFollowerM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NoOfFollowerI {

    @POST("getNoOffFollowers")
    suspend fun getNoOfFollowerI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<NoOfFollowerM>
}