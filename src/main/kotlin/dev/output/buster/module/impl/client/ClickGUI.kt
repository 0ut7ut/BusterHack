package dev.output.buster.module.impl.client

import dev.output.buster.gui.clickgui.BusterClickGUI
import dev.output.buster.module.Category
import dev.output.buster.module.Module
import dev.output.buster.util.threads.runSafe
import org.lwjgl.glfw.GLFW

object ClickGUI: Module(
    name = "ClickGUI",
    category = Category.CLIENT,
    description = "",
    defaultKeyBind = GLFW.GLFW_KEY_RIGHT_SHIFT
) {

    val background by setting("Background", true)
    val animationLength by setting("AnimationLength", 0.1f, 0.01f..1f, 0.01f)
    val red by setting("GuiRed", 175, 0..255, 1)
    val green by setting("GuiGreen", 175, 0..255, 1)
    val blue by setting("GuiBlue", 255, 0..255, 1)
    val nRed by setting("SliderRed", 120, 0..255, 1)
    val nGreen by setting("SliderGreen", 190, 0..255, 1)
    val nBlue by setting("SliderBlue", 255, 0..255, 1)
    val shadow by setting("Shadow", true)

    init {

        onEnable {
            runSafe {
                mc.setScreen(BusterClickGUI)
            }
        }

        onDisable {
            runSafe {
                mc.setScreen(null)
            }
        }

    }

}