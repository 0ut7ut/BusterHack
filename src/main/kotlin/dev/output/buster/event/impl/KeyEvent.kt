package dev.output.buster.event.impl

import dev.output.buster.event.Event
import dev.output.buster.settings.KeyBind

class KeyEvent(val key: KeyBind): Event()