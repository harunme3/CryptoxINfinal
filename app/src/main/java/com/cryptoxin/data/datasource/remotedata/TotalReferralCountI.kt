package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.totalreferralcountm.TotalReferralCountM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TotalReferralCountI {

    @POST("getTotalReferralscount")
    suspend fun getTotalReferralCountI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<TotalReferralCountM>
}