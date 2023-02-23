package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.CreateCommentBody
import com.cryptoxin.data.models.allpostm.AllPostM
import com.cryptoxin.data.models.createcommentm.CreateCommentM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface CreateCommentI {

    @POST("createComment")
    suspend fun getCreateCommentI(@Body createCommentBody: CreateCommentBody): Response<CreateCommentM>
}