package com.cryptoxin.data.datasource.roomdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cryptoxin.utilities.DataBaseConstants
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao{
    @Insert()
     fun insert(walletEntity: WalletEntity)

    @Delete
     fun deleteNote(walletEntity: WalletEntity)


    @Query("SELECT * FROM ${DataBaseConstants.WALLET_TABLE} WHERE walletId = :walletId")
     fun getWallet(walletId : Int) : Flow<WalletEntity>

//why we are not marked it suspends because live data automatically run it in background thread or can say marked it suspends automatically
    @Query("SELECT * FROM ${DataBaseConstants.WALLET_TABLE}")
    fun  getWalletList() :Flow<List<WalletEntity>>


}

