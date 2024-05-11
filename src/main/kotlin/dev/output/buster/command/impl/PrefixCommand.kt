package dev.output.buster.command.impl

import dev.output.buster.command.AbstractCommand
import dev.output.buster.manager.impl.CommandManager
import dev.output.buster.util.ChatUtils.sendMessage
import dev.output.buster.util.threads.runSafe
import net.minecraft.util.Formatting

class PrefixCommand: AbstractCommand(arrayOf("prefix")) {
    override fun run(args: Array<String>) {
        if (args.isEmpty()) {
            runSafe {
                sendMessage("prefix [prefix]")
            }
            return
        }

        if (args.size == 1) {
            CommandManager.setPrefix(args[0][0])
            runSafe {
                sendMessage("Command prefix set to ${Formatting.GOLD}${args[0][0]}")
            }
        }
    }
}