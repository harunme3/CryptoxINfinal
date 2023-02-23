package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.DeletePostBody
import com.cryptoxin.data.datasource.remotedata.DeletePostI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DeletePostR @Inject
constructor(private val deletePostI: DeletePostI) {

    fun getDeletePostR(deletePostBody: DeletePostBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = deletePostI.getDeletePostI(deletePostBody)
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