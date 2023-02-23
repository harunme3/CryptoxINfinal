package com.cryptoxin.data.datasource.remotedata


import com.cryptoxin.data.models.allpostm.AllPostM
import com.cryptoxin.data.models.appversionm.AppVersionM
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface AppVersionI {

    @GET("getCryptoxinVersion")
    suspend fun getAppVersionI(): Response<AppVersionM>
}