package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.DeletePostBody
import com.cryptoxin.data.models.deletepostm.DeletePostM
import com.cryptoxin.data.models.userpostm.UserPostM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DeletePostI {

    @POST("deletePost")
    suspend fun getDeletePostI(@Body deletePostBody: DeletePostBody): Response<DeletePostM>
}