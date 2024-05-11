package dev.output.buster.util.player

import dev.output.buster.event.SafeClientEvent
import net.minecraft.client.network.PendingUpdateManager
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.Entity
import net.minecraft.util.math.Vec3d

object PlayerUtils {

    fun SafeClientEvent.getWorldActionId(world: ClientWorld): Int {
        val pum: PendingUpdateManager = world.pendingUpdateManager
        val p = pum.sequence
        pum.close()
        return p
    }

    fun SafeClientEvent.getEyesPos(entity: Entity): Vec3d {
        return entity.pos.add(0.0, entity.getEyeHeight(entity.pose).toDouble(), 0.0)
    }

}