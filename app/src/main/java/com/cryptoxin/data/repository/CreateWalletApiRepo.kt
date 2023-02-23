package com.cryptoxin.data.repository


import androidx.lifecycle.LiveData
import com.cryptoxin.common.Resource
import com.cryptoxin.data.datasource.remotedata.CreateWalletApiInterface
import com.cryptoxin.data.datasource.roomdata.WalletDao
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreateWalletApiRepo  @Inject constructor(
    private val createWalletApiInterface: CreateWalletApiInterface,
    private val walletDao: WalletDao)
{

    fun getCreateWalletRepo() = flow {
        try {
            emit(Resource.Loading())
            val apiResponse = createWalletApiInterface.getWalletDetailInterface()
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

    fun insertWallet(walletEntity: WalletEntity) = walletDao.insert(walletEntity)

 }

