package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.bonustimem.BonusTimeM
import com.cryptoxin.data.models.followingm.FollowingM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BonusTimeI {

    @POST("nextbonustime")
    suspend fun getBonusTimeI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<BonusTimeM>
}