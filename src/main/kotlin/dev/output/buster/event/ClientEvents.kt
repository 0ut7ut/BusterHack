package dev.output.buster.event

import dev.output.buster.event.impl.DisconnectEvent
import dev.output.buster.event.impl.GameLoopEvent
import dev.output.buster.event.impl.JoinWorldEvent
import dev.output.buster.util.Wrapper
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.network.ClientPlayerInteractionManager
import net.minecraft.client.world.ClientWorld

abstract class AbstractClientEvent {
    val mc = Wrapper.mc
    abstract val world: ClientWorld?
    abstract val player: ClientPlayerEntity?
    abstract val connection: ClientPlayNetworkHandler?
    abstract val playerController: ClientPlayerInteractionManager?
}

open class ClientEvent: AbstractClientEvent() {
    final override val world: ClientWorld? = mc.world
    final override val player: ClientPlayerEntity? = mc.player
    final override val connection: ClientPlayNetworkHandler? = mc.networkHandler
    final override val playerController: ClientPlayerInteractionManager? = mc.interactionManager

    inline operator fun <T> invoke(block: ClientEvent.() -> T) = run(block)
}

open class SafeClientEvent internal constructor(
    override val world: ClientWorld,
    override val player: ClientPlayerEntity,
    override val connection: ClientPlayNetworkHandler,
    override val playerController: ClientPlayerInteractionManager
): AbstractClientEvent() {

    inline operator fun <T> invoke(block: SafeClientEvent.() -> T) = run(block)

    companion object {

        var instance: SafeClientEvent? = null; private set

        init {
            eventListener<GameLoopEvent.Tick>(alwaysListening = true) {
                update()
            }

            eventListener<DisconnectEvent>(alwaysListening = true) {
                reset()
            }

            eventListener<JoinWorldEvent>(alwaysListening = true) {
                reset()
            }
        }

        private fun update() {
            val world = Wrapper.world ?: return
            val player = Wrapper.player ?: return
            val connection = Wrapper.mc.networkHandler ?: return
            val playerController = Wrapper.mc.interactionManager ?: return

            instance = SafeClientEvent(world, player, connection, playerController)
        }

        private fun reset() {
            instance = null
        }

    }

}
