package dev.output.buster.event.impl

import dev.output.buster.event.CancellableEvent
import net.minecraft.network.packet.Packet

sealed class PacketEvent(val packet: Packet<*>) : CancellableEvent() {
    class Receive(packet: Packet<*>) : PacketEvent(packet)
    class Send(packet: Packet<*>) : PacketEvent(packet)
    class ReceivePost(packet: Packet<*>) : PacketEvent(packet)
    class SendPost(packet: Packet<*>) : PacketEvent(packet)
}