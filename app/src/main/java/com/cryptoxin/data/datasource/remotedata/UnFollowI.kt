package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.FollowBody
import com.cryptoxin.data.models.userpostm.UserPostM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UnFollowI {

    @POST("UnFollow")
    suspend fun getUnFollowI(@Body followBody: FollowBody): Response<UserPostM>
}