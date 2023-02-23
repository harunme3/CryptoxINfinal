package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.nooffollower.NoOfFollowerM
import com.cryptoxin.data.models.postrewardm.PostRewardM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostRewardI {

    @POST("getpostreward")
    suspend fun getPostRewardI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<PostRewardM>
}