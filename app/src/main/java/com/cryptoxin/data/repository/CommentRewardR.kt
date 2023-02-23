package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.AddressPrivateKeyBody
import com.cryptoxin.data.datasource.remotedata.CommentRewardI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject




class CommentRewardR @Inject constructor(private val commentRewardI: CommentRewardI) {

    fun getCommentRewardR(addressPrivateKeyBody: AddressPrivateKeyBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = commentRewardI.getCommentRewardI(addressPrivateKeyBody)
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