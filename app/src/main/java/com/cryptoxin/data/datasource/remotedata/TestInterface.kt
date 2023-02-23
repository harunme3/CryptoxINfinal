package com.cryptoxin.data.datasource.remotedata



import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.signupbonusm.SignupBonusM
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface TestInterface {
    @POST("singupbonace")
    fun getTestInterface(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Call<SignupBonusM>

}