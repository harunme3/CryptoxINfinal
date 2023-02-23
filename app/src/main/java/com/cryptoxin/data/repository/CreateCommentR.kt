package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.CreateCommentBody
import com.cryptoxin.data.datasource.remotedata.CreateCommentI
import com.cryptoxin.data.datasource.remotedata.FollowingI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CreateCommentR @Inject
constructor(private val createCommentI: CreateCommentI) {

    fun getCreateCommentR(createCommentBody: CreateCommentBody) = flow {
        try {
            emit(Resource.Loading())
            Log.d("1111",createCommentBody.toString())
            val apiResponse = createCommentI.getCreateCommentI(createCommentBody)

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