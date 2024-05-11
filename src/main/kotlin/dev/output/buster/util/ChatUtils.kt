package dev.output.buster.util

import dev.output.buster.event.SafeClientEvent
import net.minecraft.text.Text
import net.minecraft.util.Formatting

object ChatUtils {

    fun SafeClientEvent.sendMessage(message: CharSequence) {

        mc.inGameHud.chatHud.addMessage(
            Text.of("${Formatting.AQUA}[Buster] ${Formatting.WHITE}" + message.toString())
        )
    }

}