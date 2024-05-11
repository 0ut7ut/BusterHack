package dev.output.buster.util.interfaces

interface Nameable {
    val name: CharSequence

    fun nameAsString() = name.toString()
}