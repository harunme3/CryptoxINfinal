package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.signupbonusm.SignupBonusM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupBonusI {

    @POST("singupbonace")
    suspend fun getSignupBonusI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<SignupBonusM>
}