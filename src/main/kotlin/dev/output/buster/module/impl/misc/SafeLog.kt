package dev.output.buster.module.impl.misc

import dev.output.buster.event.SafeClientEvent
import dev.output.buster.event.impl.TickEvent
import dev.output.buster.event.safeEventListener
import dev.output.buster.module.Category
import dev.output.buster.module.Module
import dev.output.buster.util.Wrapper.mc
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket
import net.minecraft.text.Text

/**
@author Administrator
 */
object SafeLog : Module(
    name = "SafeLog",
    description = "SafeLog",
    category = Category.MISC
) {
    private val health by setting("Health", 10f, 0f..20f, 0.5f)
    init {
        safeEventListener<TickEvent.Post> {
            if (mc.player!!.health < health){
                disable()
                onDisconnect()
            }
        }
    }
    private fun onDisconnect() {
        val player = mc.player
        val world = mc.world

        if (player == null || world == null) {
            return
        }

        player.networkHandler.onDisconnect(
            DisconnectS2CPacket(
                Text.of("[AutoLog] Current health is not supported.")
            )
        )
    }
}
