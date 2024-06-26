package dev.output.buster.command.impl

import dev.output.buster.command.AbstractCommand
import dev.output.buster.manager.impl.FriendManager
import dev.output.buster.util.ChatUtils.sendMessage
import dev.output.buster.util.threads.runSafe
import net.minecraft.util.Formatting

class FriendCommand: AbstractCommand(arrayOf("friend")) {
    override fun run(args: Array<String>) {
        if (args.isEmpty()) {
            runSafe {
                sendMessage("friend [add/del/list] [player name]")
            }
            return
        }

        runSafe {
            if (args.size == 2) {
                when (args[0]) {
                    "add" -> {
                        if (FriendManager.isFriend(args[1])) {
                            sendMessage("${args[1]} is your friend")
                            return
                        }
                        FriendManager.friends.add(args[1])
                        sendMessage("Added friend ${args[1]}")
                    }
                    "del" -> {
                        if (!FriendManager.isFriend(args[1])) {
                            sendMessage("${args[1]} isn't your friend!")
                        } else {
                            FriendManager.friends.remove(args[1])
                            sendMessage("Removed friend ${args[1]}")
                        }
                    }
                }
            } else {
                if (args[0] == "list") {
                    var str = "${Formatting.GRAY}Friends: "
                    FriendManager.friends.forEach {
                        str += "$it "
                    }
                    sendMessage(str)
                }
            }
        }

    }

}