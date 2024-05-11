package dev.output.buster.command.impl

import dev.output.buster.command.AbstractCommand
import dev.output.buster.manager.impl.ModuleManager
import dev.output.buster.util.ChatUtils.sendMessage
import dev.output.buster.util.threads.runSafe

class ToggleCommand: AbstractCommand(arrayOf("t", "toggle")) {
    override fun run(args: Array<String>) {
        if (args.isEmpty()) {
            runSafe {
                sendMessage("toggle [module name]")
            }
            return
        }

        if (args.size == 1) {
            ModuleManager.modules().forEach {
                if (it.nameAsString() == args[0]) {
                    it.toggle()
                    return@forEach
                }
            }
        }
    }
}