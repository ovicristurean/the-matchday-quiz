package com.ovidiucristurean.thematchdayquiz

import android.os.Looper

actual class ThreadChecker {
    actual val isOnMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()
}
