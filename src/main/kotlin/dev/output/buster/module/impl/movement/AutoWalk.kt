package dev.output.buster.module.impl.movement

import dev.output.buster.event.impl.TickEvent
import dev.output.buster.event.safeEventListener
import dev.output.buster.module.Category
import dev.output.buster.module.Module
import dev.output.buster.util.Wrapper.mc

/**
@author Administrator
 */
object AutoWalk : Module(
    name = "AutoWalk",
    category = Category.MOVEMENT,
    description = "Automatically walks forward."
) {
    init {
        safeEventListener<TickEvent.Post> {
            mc.options.forwardKey.isPressed = true
        }
        onDisable {
            mc.options.forwardKey.isPressed = false
        }
    }
}