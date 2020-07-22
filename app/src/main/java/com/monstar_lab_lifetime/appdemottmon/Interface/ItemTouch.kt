package com.monstar_lab_lifetime.appdemottmon.Interface

interface ItemTouch {
    fun onMove(oldPosition: Int, newPosition: Int)

    fun swipe(position: Int, direction: Int)
}