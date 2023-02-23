package com.cryptoxin.uistate

import com.cryptoxin.data.models.getuser.GetUserModel
import com.cryptoxin.data.models.importaccountmodel.ImportAccountModel



sealed class GetUserState {
    object Empty : GetUserState()
    object Loading : GetUserState()
    class Loaded(val data: GetUserModel) : GetUserState()
    class Error(val message: String) : GetUserState()
}