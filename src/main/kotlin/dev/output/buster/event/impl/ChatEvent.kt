package dev.output.buster.event.impl

import dev.output.buster.event.CancellableEvent

class ChatEvent(val message: String): CancellableEvent()