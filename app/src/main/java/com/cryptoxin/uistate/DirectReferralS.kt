package com.cryptoxin.uistate

import com.cryptoxin.data.models.directreferralm.DirectReferralM


sealed class DirectReferralS {
    object Empty : DirectReferralS()
    object Loading : DirectReferralS()
    class Loaded(val data: DirectReferralM) : DirectReferralS()
    class Error(val message: String) : DirectReferralS()
}