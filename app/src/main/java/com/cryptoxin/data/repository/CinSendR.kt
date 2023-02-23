package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.CinSendBody
import com.cryptoxin.data.datasource.remotedata.CinSendI
import com.cryptoxin.data.datasource.remotedata.NoOfFollowerI
import com.cryptoxin.data.datasource.remotedata.ProfileI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CinSendR @Inject
constructor(private val cinSendI: CinSendI) {

    fun getCinSendR(cinSendBody: CinSendBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = cinSendI.getCinSendI(cinSendBody)
            if (apiResponse.isSuccessful) {

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