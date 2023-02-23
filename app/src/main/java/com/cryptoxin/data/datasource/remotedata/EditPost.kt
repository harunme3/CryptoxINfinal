package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.EditPostBody
import com.cryptoxin.data.models.userpostm.UserPostM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface EditPostI {

    @POST("editPost")
    suspend fun getEditPostI(@Body editPostBody: EditPostBody): Response<UserPostM>
}