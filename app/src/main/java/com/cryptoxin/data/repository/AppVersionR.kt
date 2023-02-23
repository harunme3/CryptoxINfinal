package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.datasource.remotedata.AllPostI
import com.cryptoxin.data.datasource.remotedata.AppVersionI
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AppVersionR @Inject
constructor(private val appVersionI: AppVersionI) {
    fun getAppVersionR() = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = appVersionI.getAppVersionI()
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