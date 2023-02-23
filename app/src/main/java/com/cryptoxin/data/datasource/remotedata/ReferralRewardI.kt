package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.nooffollower.NoOfFollowerM
import com.cryptoxin.data.models.referralrewardm.ReferralRewardM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ReferralRewardI {

    @POST("getreferralreward")
    suspend fun getReferralRewardI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<ReferralRewardM>
}