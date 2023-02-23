package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.commentrewardsumm.CommentRewardSumM
import com.cryptoxin.data.models.followingm.FollowingM
import com.cryptoxin.data.models.likerewardsumm.LikeRewardSumM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LikeRewardSumI {

    @POST("sumlikereward")
    suspend fun getLikeRewardSumI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<LikeRewardSumM>
}