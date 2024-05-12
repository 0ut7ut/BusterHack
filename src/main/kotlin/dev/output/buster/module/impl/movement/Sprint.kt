package dev.output.buster.module.impl.movement
import dev.output.buster.event.impl.TickEvent
import dev.output.buster.event.safeEventListener
import dev.output.buster.module.Category
import dev.output.buster.module.Module
object Sprint : Module(
    name = "Sprint",
    category = Category.MOVEMENT,
    description = "Force Sprint",
) {
    init {
        safeEventListener<TickEvent.Post> {
            mc.player?.isSprinting = true
        }
    }
}