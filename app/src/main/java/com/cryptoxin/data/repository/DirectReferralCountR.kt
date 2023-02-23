package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.DirectReferralCountI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class DirectReferralCountR @Inject
constructor(private val directReferralCountI: DirectReferralCountI) {

    fun getDirectReferralCountR (addressPrivateKeyBody: AddressPrivateKeyBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = directReferralCountI.getDirectReferralCountI(addressPrivateKeyBody)
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