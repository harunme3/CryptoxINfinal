package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.FollowBody
import com.cryptoxin.data.datasource.remotedata.FollowI
import com.cryptoxin.data.datasource.remotedata.UnFollowI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class UnFollowR @Inject
constructor(private val unFollowI: UnFollowI) {

    fun getUnFollowR(followBody: FollowBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = unFollowI.getUnFollowI(followBody)
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