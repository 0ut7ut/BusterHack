package dev.output.buster.module.impl.misc

import dev.output.buster.event.impl.TickEvent
import dev.output.buster.event.safeEventListener
import dev.output.buster.module.Category
import dev.output.buster.module.Module
import dev.output.buster.util.interfaces.DisplayEnum
import org.lwjgl.glfw.GLFW

object Test: Module(
    name = "Test",
    description = "Test",
    category = Category.MISC,
    defaultKeyBind = GLFW.GLFW_KEY_Y
) {

    private val enum by setting("Enum", TestEnum.BUSTER)
    private val slider by setting("Slider", 10f, 0f..20f, 0.5f)

    init {
        safeEventListener<TickEvent.Post> {
        }
    }

    private enum class TestEnum(override val displayName: CharSequence): DisplayEnum {
        BUSTER("Buster"),
        MELON("Melon")
    }

}