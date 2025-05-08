package com.bsuir.weather.utils.ext

import android.app.PendingIntent
import android.widget.RemoteViews
import com.bsuir.weather.R

fun RemoteViews.withPadding(
    padding: Int
): RemoteViews {
    return apply {
        setViewPadding(R.id.root, padding, padding, padding, padding)
    }
}

fun RemoteViews.withClickAction(
    pendingIntent: PendingIntent
): RemoteViews {
    return apply {
        setOnClickPendingIntent(R.id.root, pendingIntent)
    }
}

fun RemoteViews.withBackgroundColor(color: Int): RemoteViews {
    return apply {
        setInt(R.id.root, "setBackgroundColor", color)
    }
}
