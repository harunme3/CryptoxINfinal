package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.FollowBody
import com.cryptoxin.data.bodymodel.UnfollowListBody
import com.cryptoxin.data.models.nooffollowing.NoOfFollowingM
import com.cryptoxin.data.models.unfollowlistm.UnfollowListM
import com.cryptoxin.data.models.userpostm.UserPostM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UnfollowListI {

    @POST("UnFollowlist")
    suspend fun getUnfollowListI(@Body unfollowListBody: UnfollowListBody): Response<UnfollowListM>
}