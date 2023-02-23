package com.cryptoxin.data.datasource.remotedata


import com.cryptoxin.data.models.allpostm.AllPostM
import retrofit2.Response
import retrofit2.http.POST

interface AllPostI {

    @POST("getallPost")
    suspend fun getAllPostI(): Response<AllPostM>
}