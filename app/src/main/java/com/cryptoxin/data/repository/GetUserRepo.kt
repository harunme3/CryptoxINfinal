package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.GetUserModelBody
import com.cryptoxin.data.bodymodel.ImportAccountBody
import com.cryptoxin.data.datasource.remotedata.GetUserApiInterface
import com.cryptoxin.data.datasource.remotedata.ImportWalletApiInterface
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class GetUserRepo @Inject
constructor(private val getUserApiInterface: GetUserApiInterface) {

    fun getUserApiRepo(getUserModelBody: GetUserModelBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = getUserApiInterface.getUserInterface(getUserModelBody)
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