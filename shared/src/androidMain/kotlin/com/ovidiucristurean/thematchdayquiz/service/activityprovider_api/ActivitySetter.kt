package com.ovidiucristurean.thematchdayquiz.service.activityprovider_api

import android.app.Activity

interface ActivitySetter {
    fun set(activity: Activity)
    fun clear()
}
