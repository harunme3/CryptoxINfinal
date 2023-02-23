package com.cryptoxin.data.datasource.remotedata

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.models.nooffollower.NoOfFollowerM
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject


class UserPostR @Inject
constructor(private val userPostI: UserPostI) {

    fun getUserPostR(addressPrivateKeyBody: AddressPrivateKeyBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = userPostI.getUserPostI(addressPrivateKeyBody)
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