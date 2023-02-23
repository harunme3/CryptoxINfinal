package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.fiftylevelrewardm.FiftyLevelRewardM
import com.cryptoxin.data.models.followingm.FollowingM
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FiftyLevelRewardI {

    @POST("getparentrewardss")
    suspend fun getFiftyLevelRewardI(@Body addressPrivateKeyBody: AddressPrivateKeyBody): Response<FiftyLevelRewardM>
}