package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.dailycheckInclaimm.DailyCheckInClaimM
import com.cryptoxin.data.models.followingm.FollowingM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DailyCheckInClaimI {

    @POST("DailycheckinBonas")
    suspend fun getDailyCheckInClaimI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<DailyCheckInClaimM>
}