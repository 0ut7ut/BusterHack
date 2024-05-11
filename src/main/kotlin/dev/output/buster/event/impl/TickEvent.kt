package dev.output.buster.event.impl

import dev.output.buster.event.Event

sealed class TickEvent {

    object Post: Event()
    object Pre: Event()

}