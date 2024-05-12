package dev.output.buster.module.impl.misc
import dev.output.buster.event.impl.TickEvent
import dev.output.buster.event.safeEventListener
import dev.output.buster.module.Category
import dev.output.buster.module.Module
import net.minecraft.client.gui.screen.DeathScreen

/**
@author Administrator
 */
object AutoRespawn : Module(
    name = "AutoRespawn",
    description = "AutoRespawn",
    category = Category.MISC
){
    init {
        safeEventListener<TickEvent.Post> {
            if (mc.currentScreen is DeathScreen){
                mc.player!!.requestRespawn()
                mc.setScreen(null)
            }
        }
    }
}