package dev.output.buster.util.interfaces

interface Description {
    val description: CharSequence

    fun descriptionAsString() = description.toString()
}