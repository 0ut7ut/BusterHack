package dev.output.buster.command.impl

import dev.output.buster.command.AbstractCommand
import dev.output.buster.manager.impl.ModuleManager
import dev.output.buster.util.ChatUtils.sendMessage
import dev.output.buster.util.threads.runSafe
import net.minecraft.client.util.InputUtil
import net.minecraft.util.Formatting
import java.util.*

class BindCommand: AbstractCommand(arrayOf("bind")) {
    override fun run(args: Array<String>) {
        if (args.isEmpty()) {
            runSafe {
                sendMessage("bind [module name] [key bind]")
            }
            return
        }

        if (args.size == 2) {

            ModuleManager.modules().forEach {
                if (it.nameAsString() == args[0]) {
                    runSafe {
                        val stringKey = args[1]

                        val key = if (stringKey.lowercase() == "none") -1
                        else {
                            try {
                                InputUtil.fromTranslationKey("key.keyboard." + stringKey.lowercase(Locale.getDefault())).code
                            } catch (e: NumberFormatException) {
                                sendMessage("There is no such button")
                                return
                            }
                        }

                        if (key == 0) {
                            sendMessage("Unknown key '$stringKey'!")
                            return
                        }

                        it.keyBind.keyCode = key
                        sendMessage("Bind for " + Formatting.GREEN + it.name +
                                Formatting.WHITE + " set to " + Formatting.GRAY + stringKey.uppercase(Locale.getDefault()))
                    }
                }
            }

        }
    }
}