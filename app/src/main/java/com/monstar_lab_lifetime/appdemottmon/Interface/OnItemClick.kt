package com.monstar_lab_lifetime.appdemottmon.Interface

import com.monstar_lab_lifetime.appdemottmon.`object`.FeedData

interface OnItemClick {
    fun onClicks(feedData: FeedData, position: Int)

}