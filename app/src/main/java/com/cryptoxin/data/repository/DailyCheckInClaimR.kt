package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.DailyCheckInClaimI
import com.cryptoxin.data.datasource.remotedata.FollowingI
import com.cryptoxin.data.datasource.remotedata.TotalReferralCountI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DailyCheckInClaimR @Inject
constructor(private val dailyCheckInClaimI: DailyCheckInClaimI) {

    fun getDailyCheckInClaimR(addressPrivateKeyBody: AddressPrivateKeyBody) = flow {
        try {
            emit(Resource.Loading())
            Log.d("1111",addressPrivateKeyBody.toString())
            val apiResponse = dailyCheckInClaimI.getDailyCheckInClaimI(addressPrivateKeyBody)

            if (apiResponse.isSuccessful) {
                Log.d("1111",apiResponse.toString())
                val result = apiResponse.body()
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error("Api is unsuccessful"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("IO Exception: ${e.message}"))
        }
    }
}