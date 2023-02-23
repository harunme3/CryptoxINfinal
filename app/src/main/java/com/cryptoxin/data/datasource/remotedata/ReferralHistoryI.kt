package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.referralhistory.ReferralHistoryM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReferralHistoryI {

    @POST("getreferralRehistory")
    suspend fun getReferralHistoryI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<ReferralHistoryM>
}