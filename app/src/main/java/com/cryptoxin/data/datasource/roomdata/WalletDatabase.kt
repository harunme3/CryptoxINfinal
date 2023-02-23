package com.cryptoxin.data.datasource.roomdata

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [WalletEntity::class], version = 1)
abstract class WalletDatabase : RoomDatabase() {
    abstract fun walletDao():WalletDao
}