package com.cryptoxin.uistate

import com.cryptoxin.data.models.dailycheckInclaimm.DailyCheckInClaimM


sealed class DailyCheckInClaimS {
    object Empty : DailyCheckInClaimS()
    object Loading : DailyCheckInClaimS()
    class Loaded(val data: DailyCheckInClaimM) : DailyCheckInClaimS()
    class Error(val message: String) : DailyCheckInClaimS()
}