package dev.output.buster.manager

import dev.output.buster.IAsyncLoader

abstract class AbstractManager
@JvmOverloads constructor(override val priority: Int = 0) : IAsyncLoader