package dev.output.buster.manager.impl

import dev.output.buster.command.AbstractCommand
import dev.output.buster.command.impl.*
import dev.output.buster.event.impl.ChatEvent
import dev.output.buster.event.safeEventListener
import dev.output.buster.manager.AbstractManager
import dev.output.buster.util.ChatUtils.sendMessage

object CommandManager: AbstractManager() {

    val commandMap = hashMapOf<Array<String>, AbstractCommand>()
    private var prefix = '.'

    override suspend fun load() {
        add(ToggleCommand())
        add(BindCommand())
        add(HelpCommand())
        add(ConfigCommand())
        add(FriendCommand())
        add(PrefixCommand())
    }

    init {
        safeEventListener<ChatEvent> {
            if (it.message[0] == prefix) {
                if (!run(it.message)) {
                    sendMessage("Can't find this command")
                }
                it.cancel()
            }
        }
    }

    private fun add(cmd: AbstractCommand) {
        commandMap[cmd.key] = cmd
    }

    fun run(message: String): Boolean {
        val filter = message.split(" ").filter { it.isNotEmpty() }.toTypedArray()
        if (filter.toList().isEmpty()) return false
        val command = filter[0].substring(1)
        commandMap.forEach { (n, u) ->
            if ( n.aliasesContains(command)) {
                val args = filter.toList().toMutableList()
                args.removeFirst()
                u.run(args.toList().toTypedArray())
                return true
            }
        }
        return false
    }

    private fun Array<String>.aliasesContains(s: String): Boolean {
        return this.find { it.equals(s, true) } != null
    }

    fun getPrefix(): Char {
        return prefix
    }

    fun setPrefix(ch: Char) {
        prefix = ch
    }

}