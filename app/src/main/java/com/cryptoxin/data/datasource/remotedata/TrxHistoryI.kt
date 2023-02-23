package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.trxhistorym.TrxHistoryM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface TrxHistoryI {

    @POST("getxhs")
    suspend fun getTrxHistoryI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<TrxHistoryM>
}