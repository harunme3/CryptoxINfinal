package com.cryptoxin.uistate

import com.cryptoxin.data.models.referralhistory.ReferralHistoryM


sealed class ReferralHistoryS {
    object Empty : ReferralHistoryS()
    object Loading : ReferralHistoryS()
    class Loaded(val data: ReferralHistoryM) : ReferralHistoryS()
    class Error(val message: String) : ReferralHistoryS()
}