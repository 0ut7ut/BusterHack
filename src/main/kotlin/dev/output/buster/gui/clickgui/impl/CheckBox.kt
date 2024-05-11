package dev.output.buster.gui.clickgui.impl

import dev.output.buster.gui.clickgui.Component
import dev.output.buster.gui.clickgui.ModuleButton
import dev.output.buster.module.impl.client.ClickGUI
import dev.output.buster.settings.AbstractSetting
import dev.output.buster.settings.BooleanSetting
import dev.output.buster.util.graphics.Render2DUtils
import dev.output.buster.util.graphics.color.ColorRGB
import dev.output.buster.util.Wrapper.mc
import dev.output.buster.util.font.TextUtils
import net.minecraft.client.gui.DrawContext


class CheckBox(
    setting: AbstractSetting<*>,
    parent: ModuleButton,
    offset: Float,
    height: Float
): Component(setting, parent, offset, height) {

    private val boolSet: BooleanSetting = setting as BooleanSetting

    override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        if (!boolSet.visibility.invoke()) {
            return
        }

        if (isHovered(mouseX.toDouble(), mouseY.toDouble())) Render2DUtils.drawRect(context.matrices,
            parent.parent.x, parent.parent.y + parent.offset + offset,
            parent.parent.width,
            height,
            ColorRGB(ClickGUI.red, ClickGUI.green, ClickGUI.blue, 80))

        val textOffset = (height / 2) - mc.textRenderer.fontHeight / 2

        TextUtils.drawString(context, boolSet.name.toString(),
            parent.parent.x + parent.textOffset, parent.parent.y + parent.offset + offset + textOffset,
            if (boolSet.value) ColorRGB(ClickGUI.red, ClickGUI.green, ClickGUI.blue)
            else ColorRGB(255, 255, 255), ClickGUI.shadow
        )
    }

    override fun mouseClicked(mouseX: Double, mouseY: Double, button: Int) {
        if (!boolSet.visibility.invoke()) return
        if (isHovered(mouseX, mouseY) && button == 0) {
            boolSet.value = !boolSet.value
        }
    }

    override fun mouseReleased(mouseX: Double, mouseY: Double, button: Int) {
        if (!boolSet.visibility.invoke()) return
    }

    override fun keyReleased(keyCode: Int, scanCode: Int, modifiers: Int) {
        if (!boolSet.visibility.invoke()) return
    }

}