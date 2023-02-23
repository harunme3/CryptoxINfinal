package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.cinbalancem.CinBalanceM
import com.cryptoxin.data.models.nooffollowing.NoOfFollowingM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface CinBalanceI {
    @POST("balance")
    suspend fun getCinBalanceI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<CinBalanceM>
}