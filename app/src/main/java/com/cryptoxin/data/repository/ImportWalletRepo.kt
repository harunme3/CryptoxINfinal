package com.cryptoxin.data.repository

import android.util.Log
import com.cryptoxin.common.Resource
import com.cryptoxin.data.bodymodel.ImportAccountBody
import com.cryptoxin.data.datasource.remotedata.ImportWalletApiInterface
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ImportWalletRepo @Inject
constructor(private val importWalletApiInterface: ImportWalletApiInterface) {

    fun getImportWalletRepo(importAccountBody: ImportAccountBody) = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = importWalletApiInterface.importWalletInterface(importAccountBody)

            if (apiResponse.isSuccessful) {

                val result = apiResponse.body()
                Log.e("1112",result.toString())
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error("Api is unsuccessful"))
            }
        } catch (e: Exception) {
            emit(Resource.Error("IO Exception: ${e.message}"))
        }
    }
}