package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.CheckUpdateUserNameBody
import com.cryptoxin.data.models.checkupdateusernamem.CheckUpdateUserNameM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface CheckUpdateUserNameI {
    @POST("changeUsername")
    suspend fun getCheckUpdateUserNameI(@Body checkUpdateUserNameBody: CheckUpdateUserNameBody): Response<CheckUpdateUserNameM>
}