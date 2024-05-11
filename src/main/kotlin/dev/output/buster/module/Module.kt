package dev.output.buster.module

import dev.output.buster.event.EventBus
import dev.output.buster.module.impl.misc.Notifications
import dev.output.buster.settings.*
import dev.output.buster.util.ChatUtils.sendMessage
import dev.output.buster.util.interfaces.Description
import dev.output.buster.util.interfaces.Nameable
import dev.output.buster.util.threads.runSafe
import net.minecraft.util.Formatting
import java.util.concurrent.CopyOnWriteArrayList

abstract class Module(

    override val name: CharSequence,
    val category: Category,
    override val description: CharSequence,
    defaultEnable: Boolean = false,
    defaultKeyBind: Int = -1

): Nameable, Description, SettingsDesigner<Module> {

    private val enableCustomers = CopyOnWriteArrayList<() -> Unit>()
    private val disableCustomers = CopyOnWriteArrayList<() -> Unit>()

    val settings = CopyOnWriteArrayList<AbstractSetting<*>>()

    private val toggleSetting0 = BooleanSetting("Toggle", false) { false }
    var isEnabled by toggleSetting0
    val isDisabled get() = !isEnabled

    private val keyBind0 = KeyBindSetting("KeyBind", KeyBind(KeyBind.Type.KEYBOARD, defaultKeyBind, 1))
    val keyBind by keyBind0

    init {

        settings.add(toggleSetting0)
        settings.add(keyBind0)

        toggleSetting0.onChangeValue {
            if (isEnabled) {
                enableCustomers.forEach { it.invoke() }
            } else {
                disableCustomers.forEach { it.invoke() }
            }
        }

        enableCustomers.add {
            EventBus.subscribe(this)
            runSafe {
                if (Notifications.isEnabled) sendMessage("$name ${Formatting.GREEN}Enabled")
            }
        }

        disableCustomers.add {
            EventBus.unsubscribe(this)
            runSafe {
                if (Notifications.isEnabled) sendMessage("$name ${Formatting.RED}Disabled")
            }
        }

        if (defaultEnable) enable()

    }

    fun toggle() = if (isEnabled) disable() else enable()

    open fun onEnable(run: () -> Unit) = enableCustomers.add(run)
    open fun onDisable(run: () -> Unit) = disableCustomers.add(run)

    fun enable() {
        isEnabled = true
    }

    fun disable() {
        isEnabled = false
    }

    open fun hudInfo(): String {
        return ""
    }

    override fun <S : AbstractSetting<*>> Module.setting(setting: S): S {
        return setting.apply { settings.add(setting) }
    }


}