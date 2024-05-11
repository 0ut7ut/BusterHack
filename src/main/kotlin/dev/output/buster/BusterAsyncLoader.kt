package dev.output.buster

import dev.output.buster.event.EventBus
import dev.output.buster.manager.AbstractManager
import dev.output.buster.util.ClassUtils.instance
import dev.output.buster.helper.classes
import java.lang.reflect.Modifier

interface IAsyncLoader {
    val priority: Int
    suspend fun load()
}

object BusterAsyncLoader : IAsyncLoader {
    override val priority: Int = Int.MAX_VALUE
    override suspend fun load() {
        classes.asSequence()
            .filter { Modifier.isFinal(it.modifiers) }
            .filter { it.name.startsWith("dev.output.buster.manager.impl") }
            .filter { AbstractManager::class.java.isAssignableFrom(it) }
            .map { it.instance as AbstractManager }
            .sortedByDescending { it.priority }
            .forEach {
                it.load()
                EventBus.subscribe(it)
            }
    }
}
