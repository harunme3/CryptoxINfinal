package com.cryptoxin.data.repository

import com.cryptoxin.common.Resource
import com.cryptoxin.data.datasource.remotedata.CreateWalletApiInterface
import com.cryptoxin.data.datasource.roomdata.WalletDao
import com.cryptoxin.data.datasource.roomdata.WalletEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class WalletR  @Inject constructor(
    private val walletDao: WalletDao
)
{

    fun insertWallet(walletEntity: WalletEntity) = walletDao.insert(walletEntity)
    fun deleteWallet(walletEntity: WalletEntity) = walletDao.deleteNote(walletEntity)
    fun getWallet(walletId : Int) : Flow<WalletEntity> = walletDao.getWallet(walletId)
    fun getAllWallet(): Flow<List<WalletEntity>> = walletDao.getWalletList()

}