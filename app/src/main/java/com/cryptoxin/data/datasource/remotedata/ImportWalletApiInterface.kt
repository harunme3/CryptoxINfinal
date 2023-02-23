package com.cryptoxin.data.datasource.remotedata


import com.cryptoxin.data.bodymodel.ImportAccountBody
import com.cryptoxin.data.models.importaccountmodel.ImportAccountModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ImportWalletApiInterface {
    @POST("importWallet")
    suspend fun importWalletInterface(@Body importAccountBody: ImportAccountBody): Response<ImportAccountModel>
}