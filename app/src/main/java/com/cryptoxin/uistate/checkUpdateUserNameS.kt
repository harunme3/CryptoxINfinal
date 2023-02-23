package com.cryptoxin.uistate

import com.cryptoxin.data.models.checkupdateusernamem.CheckUpdateUserNameM


sealed class CheckUpdateUserNameS {
    object Empty : CheckUpdateUserNameS()
    object Loading : CheckUpdateUserNameS()
    class Loaded(val data: CheckUpdateUserNameM) : CheckUpdateUserNameS()
    class Error(val message: String) : CheckUpdateUserNameS()
}