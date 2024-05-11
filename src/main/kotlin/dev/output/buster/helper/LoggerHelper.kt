package dev.output.buster.helper

import dev.output.buster.Buster

object LoggerHelper {

    fun logInfo(info: String) = Buster.LOGGER.info(info)
    fun logInfo(info: CharSequence) = logInfo(info.toString())

}