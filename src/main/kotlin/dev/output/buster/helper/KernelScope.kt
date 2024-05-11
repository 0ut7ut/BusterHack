package dev.output.buster.helper

import dev.output.buster.util.ClassUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

internal object KernelScope : CoroutineScope {
    override val coroutineContext: CoroutineContext = KernelScheduler + CoroutineName("Buster-KernelScope")
}

internal object KernelScheduler : CoroutineDispatcher() {
    private val singlePoolExecutor = Executors.newSingleThreadExecutor { Thread(it, "Buster-Kernel") }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        singlePoolExecutor.submit(block)
    }
}

val classes = ClassUtils.findClasses("dev.output.buster") {
    !it.contains("asm")
}