package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.LikePostBody
import com.cryptoxin.data.models.likepostm.LikePostM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LikePostI {

    @POST("likePost")
    suspend fun getLikePostI(@Body likePostBody: LikePostBody): Response<LikePostM>
}