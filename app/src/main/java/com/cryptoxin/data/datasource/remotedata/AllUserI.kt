package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.models.alluserm.AllUserM
import retrofit2.Response
import retrofit2.http.POST

interface AllUserI {

    @POST("getallUserr")
    suspend fun getAllUserI(): Response<AllUserM>
}