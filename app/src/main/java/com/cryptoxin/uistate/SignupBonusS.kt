package com.cryptoxin.uistate

import com.cryptoxin.data.models.signupbonusm.SignupBonusM


sealed class SignupBonusS {
    object Empty : SignupBonusS()
    object Loading : SignupBonusS()
    class Loaded(val data: SignupBonusM) : SignupBonusS()
    class Error(val message: String) : SignupBonusS()
}