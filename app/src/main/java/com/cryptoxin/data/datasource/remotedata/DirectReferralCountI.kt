package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.directreferralcountm.DirectReferralCountM
import com.cryptoxin.data.models.nooffollower.NoOfFollowerM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DirectReferralCountI {

    @POST("getdirectReferralscount")
    suspend fun getDirectReferralCountI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<DirectReferralCountM>
}