package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.followingm.FollowingM
import com.cryptoxin.data.models.postrewardsumm.PostRewardSumM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PostRewardSumI {

    @POST("sumpostreward")
    suspend fun getPostRewardSumI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<PostRewardSumM>
}