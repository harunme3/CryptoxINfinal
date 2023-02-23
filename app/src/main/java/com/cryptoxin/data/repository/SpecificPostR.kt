package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.SpecificPostBody
import com.cryptoxin.data.datasource.remotedata.FollowingI
import com.cryptoxin.data.datasource.remotedata.SpecificPostI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class SpecificPostR @Inject
constructor(private val specificPostI: SpecificPostI) {

    fun getSpecificPostR(specificPostBody: SpecificPostBody) = flow {
        try {
            emit(Resource.Loading())
            Log.d("1111",specificPostBody.toString())
            val apiResponse = specificPostI.getSpecificPostI(specificPostBody)

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