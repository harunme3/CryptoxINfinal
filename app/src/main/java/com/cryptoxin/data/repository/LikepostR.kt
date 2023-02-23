package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.LikePostBody
import com.cryptoxin.data.datasource.remotedata.LikePostI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class LikePostR @Inject
constructor(private val likePostI: LikePostI) {

    fun getLikePostR(likePostBody: LikePostBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = likePostI.getLikePostI(likePostBody)
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