package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.GetUserModelBody
import com.cryptoxin.data.bodymodel.RegisterBody
import com.cryptoxin.data.datasource.remotedata.GetUserApiInterface
import com.cryptoxin.data.datasource.remotedata.RegistrationInterface
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class RegistrationRepo @Inject
constructor(private val registrationInterface: RegistrationInterface) {

    fun registrationRepoCall(registerBody: RegisterBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = registrationInterface.registerUserInterface(registerBody)
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