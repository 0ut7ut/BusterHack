package dev.output.buster.manager.impl

import dev.output.buster.event.impl.PlayerMotionEvent
import dev.output.buster.manager.AbstractManager

object EventAccessManager: AbstractManager() {
    private var playerMotion: PlayerMotionEvent? = null

    fun getData(): PlayerMotionEvent? {
        if (playerMotion != null) {
            return playerMotion
        }
        return null
    }

    fun setData(e: PlayerMotionEvent) {
        playerMotion = e
    }

    override suspend fun load() {
    }
}