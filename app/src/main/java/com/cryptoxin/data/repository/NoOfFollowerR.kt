package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.NoOfFollowerI
import com.cryptoxin.data.datasource.remotedata.ProfileI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NoOfFollowerR @Inject
constructor(private val noOfFollowerI: NoOfFollowerI) {

    fun getNoOfFollowerR(addressPrivateKeyBody: AddressPrivateKeyBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = noOfFollowerI.getNoOfFollowerI(addressPrivateKeyBody)
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