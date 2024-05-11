package dev.output.buster.module

import dev.output.buster.util.interfaces.DisplayEnum

enum class Category(override val displayName: CharSequence): DisplayEnum {

    COMBAT("Combat"),
    MISC("Misc"),
    RENDER("Render"),
    PLAYER("Player"),
    MOVEMENT("Movement"),
    CLIENT("Client")

}