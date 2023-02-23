package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.CinSendBody
import com.cryptoxin.data.models.cinsendm.CinSendM
import com.cryptoxin.data.models.nooffollowing.NoOfFollowingM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CinSendI {
    @POST("cinsend")
    suspend fun getCinSendI(@Body cinSendBody: CinSendBody): Response<CinSendM>
}