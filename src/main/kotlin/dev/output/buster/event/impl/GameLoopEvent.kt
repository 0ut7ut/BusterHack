package dev.output.buster.event.impl

import dev.output.buster.event.Event

sealed class GameLoopEvent {

    object Tick: Event()

}