package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.commentrewardm.CommentRewardM
import com.cryptoxin.data.models.nooffollower.NoOfFollowerM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CommentRewardI {

    @POST("getCommentreward")
    suspend fun getCommentRewardI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<CommentRewardM>
}