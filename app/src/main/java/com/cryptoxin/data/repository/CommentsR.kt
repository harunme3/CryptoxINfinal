package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.bodymodel.CommentsBody
import com.cryptoxin.data.datasource.remotedata.CommentsI
import com.cryptoxin.data.datasource.remotedata.FollowingI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class CommentsR @Inject
constructor(private val commentsI: CommentsI) {

    fun getCommentsR(commentsBody: CommentsBody) = flow {
        try {
            emit(Resource.Loading())
            Log.d("1111",commentsBody.toString())
            val apiResponse = commentsI.getCommentsI(commentsBody)

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