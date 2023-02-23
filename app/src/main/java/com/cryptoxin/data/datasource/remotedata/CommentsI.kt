package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.CommentsBody
import com.cryptoxin.data.models.allpostm.AllPostM
import com.cryptoxin.data.models.commentsm.CommentsM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST



interface CommentsI {

    @POST("getComments")
    suspend fun getCommentsI(@Body commentsBody: CommentsBody): Response<CommentsM>
}