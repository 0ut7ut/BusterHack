package dev.output.buster

import dev.output.buster.helper.KernelScope
import dev.output.buster.helper.LoggerHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory
import kotlin.system.measureTimeMillis

object Buster: ClientModInitializer {

    private val MOD_META = FabricLoader.getInstance().getModContainer("buster").orElseThrow().metadata

    @JvmField val NAME = MOD_META.name
    @JvmField val VERSION = MOD_META.version
    @JvmField val LOGGER = LoggerFactory.getLogger("buster")


    override fun onInitializeClient() {

        LoggerHelper.logInfo("$NAME $VERSION")

        KernelScope.launch {
            val asyncLoaderTime = measureTimeMillis {
                withContext(Dispatchers.IO) {
                    BusterAsyncLoader.load()
                }
            }
            LoggerHelper.logInfo("Buster Loaded Compile!, took ${asyncLoaderTime}ms")
        }


    }

}