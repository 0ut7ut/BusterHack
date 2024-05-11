package dev.output.buster.command.impl

import dev.output.buster.command.AbstractCommand
import dev.output.buster.manager.impl.CommandManager
import dev.output.buster.util.ChatUtils.sendMessage
import dev.output.buster.util.threads.runSafe
import net.minecraft.util.Formatting

class HelpCommand: AbstractCommand(arrayOf("help")) {
    override fun run(args: Array<String>) {

        runSafe {
            sendMessage("${Formatting.DARK_AQUA}Buster Client")
            sendMessage("commands:")

            CommandManager.commandMap.forEach { (t, _) ->
                var str = ""
                t.forEach {
                    str += "$it "
                }

                sendMessage("${Formatting.GRAY}$str")
            }
        }

    }
}