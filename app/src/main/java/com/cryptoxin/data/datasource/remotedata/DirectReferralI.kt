package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.directreferralm.DirectReferralM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DirectReferralI {

    @POST("getListReferrals")
    suspend fun getDirectReferralI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<DirectReferralM>
}