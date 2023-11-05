package com.ovidiucristurean.thematchdayquiz.service.activityprovider_implementation

import android.app.Activity
import com.ovidiucristurean.thematchdayquiz.service.activityprovider_api.ActivityProvider
import com.ovidiucristurean.thematchdayquiz.service.activityprovider_api.ActivitySetter

class ActivityProviderImpl : ActivityProvider, ActivitySetter {
    private var activity: Activity? = null

    override fun set(activity: Activity) {
        this.activity = activity
    }

    override fun clear() {
        activity = null
    }

    override fun get(): Activity {
        return activity ?: throw Throwable("Activity does not exist")
    }
}
