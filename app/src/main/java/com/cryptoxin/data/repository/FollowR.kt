package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.FollowBody
import com.cryptoxin.data.datasource.remotedata.FollowI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FollowR @Inject
constructor(private val followI: FollowI) {

    fun getFollowR(followBody: FollowBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = followI.getFollowI(followBody)

            if (apiResponse.isSuccessful) {
                Log.e("1111",apiResponse.body().toString())
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