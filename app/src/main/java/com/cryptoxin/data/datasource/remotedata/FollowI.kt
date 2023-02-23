package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.FollowBody
import com.cryptoxin.data.models.followm.FollowM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FollowI {

    @POST("Follow")
    suspend fun getFollowI(@Body followBody: FollowBody): Response<FollowM>
}