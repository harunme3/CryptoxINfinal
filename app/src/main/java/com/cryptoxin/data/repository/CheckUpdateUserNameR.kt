package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.CheckUpdateUserNameBody
import com.cryptoxin.data.datasource.remotedata.CheckUpdateUserNameI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CheckUpdateUserNameR @Inject
constructor(private val checkUpdateUserNameI: CheckUpdateUserNameI) {

    fun getCheckUpdateUserNameR(checkUpdateUserNameBody: CheckUpdateUserNameBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = checkUpdateUserNameI.getCheckUpdateUserNameI(checkUpdateUserNameBody)
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