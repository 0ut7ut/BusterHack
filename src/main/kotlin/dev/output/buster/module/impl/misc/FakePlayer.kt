package dev.output.buster.module.impl.misc

import com.mojang.authlib.GameProfile
import dev.output.buster.module.Category
import dev.output.buster.module.Module
import dev.output.buster.util.Wrapper
import net.minecraft.client.network.OtherClientPlayerEntity
import net.minecraft.entity.Entity
import java.util.*

/**
@author Administrator
 */
object FakePlayer : Module(
    name = "FakePlayer",
    description = "FakePlayer",
    category = Category.MISC
){
    var fakePlayer: OtherClientPlayerEntity? = null
    init {
        onEnable {
            fakePlayer = OtherClientPlayerEntity(
                Wrapper.mc.world, GameProfile(
                    UUID.fromString("11451466-6666-6666-6666-666666666600"), "FakePlayer"
                )
            )
            fakePlayer!!.copyPositionAndRotation(Wrapper.mc.player)
            fakePlayer!!.getInventory()
                .clone(Wrapper.mc.player?.inventory)
            Wrapper.mc.world?.addPlayer(-1, fakePlayer)
        }
        onDisable {
            fakePlayer?.kill()
            fakePlayer?.setRemoved(Entity.RemovalReason.KILLED)
            fakePlayer?.onRemoved()
        }
    }
}