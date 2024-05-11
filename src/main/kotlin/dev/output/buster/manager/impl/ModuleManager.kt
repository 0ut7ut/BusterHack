package dev.output.buster.manager.impl

import dev.output.buster.event.impl.KeyEvent
import dev.output.buster.event.safeEventListener
import dev.output.buster.helper.LoggerHelper
import dev.output.buster.helper.classes
import dev.output.buster.manager.AbstractManager
import dev.output.buster.util.ClassUtils.instance
import dev.output.buster.module.Module
import java.lang.reflect.Modifier
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.system.measureTimeMillis

object ModuleManager: AbstractManager() {

    init {
        safeEventListener<KeyEvent> { event ->
            moduleImpls.forEach {
                if (it.keyBind == event.key) {
                    it.toggle()
                }
            }
        }
    }

    private val moduleImpls = CopyOnWriteArrayList<Module>()

    override suspend fun load() {
        val time = measureTimeMillis {
            classes.asSequence()
                .filter { Modifier.isFinal(it.modifiers) }
                .filter { it.name.startsWith("dev.output.buster.module.impl") }
                .filter { Module::class.java.isAssignableFrom(it) }
                .map { it.instance as Module }
                .forEach {
                    moduleImpls.add(it)
                }

            moduleImpls.sortBy { it.name.toString() }
        }

        LoggerHelper.logInfo("Loaded ${moduleImpls.size} module(s), it took time: ${time}ms")
    }

    fun modules(): List<Module> = moduleImpls.toList()

}