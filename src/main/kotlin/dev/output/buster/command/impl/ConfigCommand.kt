package dev.output.buster.command.impl

import dev.output.buster.command.AbstractCommand
import dev.output.buster.manager.impl.ConfigManager
import dev.output.buster.util.ChatUtils.sendMessage
import dev.output.buster.util.threads.runSafe

class ConfigCommand: AbstractCommand(arrayOf("config", "save")) {
    override fun run(args: Array<String>) {
        ConfigManager.saveAll()
        runSafe {
            sendMessage("Config saved!")
        }
    }
}