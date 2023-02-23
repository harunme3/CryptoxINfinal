package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.SpecificPostBody
import com.cryptoxin.data.models.allpostm.AllPostM
import com.cryptoxin.data.models.specificpostm.SpecificPostM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface SpecificPostI {

    @POST("getpostbyid")
    suspend fun getSpecificPostI(@Body specificPostBody: SpecificPostBody): Response<SpecificPostM>
}