package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.NoOfFollowingI
import com.cryptoxin.data.datasource.remotedata.PostRewardSumI
import com.cryptoxin.data.datasource.remotedata.ProfileI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PostRewardSumR @Inject constructor(private val postRewardSumI: PostRewardSumI) {

    fun getPostRewardSumR(addressPrivateKeyBody: AddressPrivateKeyBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = postRewardSumI.getPostRewardSumI(addressPrivateKeyBody)
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