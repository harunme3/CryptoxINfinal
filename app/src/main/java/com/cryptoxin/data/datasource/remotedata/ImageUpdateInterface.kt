package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.GetUserModelBody
import com.cryptoxin.data.models.getuser.GetUserModel
import com.cryptoxin.data.models.imageupdatemodel.ImageUpdateModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ImageUpdateInterface {

    @Multipart
    @POST("upload")
    suspend fun getImageUpdate(
        @Part file:List<MultipartBody.Part>,
        @Part("myAddress") myAddress: RequestBody,
        @Part("privateKey") privateKey: RequestBody,
        @Part("type") type: RequestBody,
        @Part("_content") _content: RequestBody,
        @Part("_hashtag") _hashtag: RequestBody,
        @Part("videoHash") videoHash: RequestBody
    ): Response<ImageUpdateModel>

}