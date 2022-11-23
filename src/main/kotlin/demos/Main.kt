package demos

import com.github.ajalt.mordant.rendering.TextColors

fun main(_args: Array<String>) {
    println("demos.BinaryTreeDemo...")
    BinaryTreeDemo(TextColors.brightWhite on TextColors.red).manualTest()

    println("demos.SidewinderDemo...")
    SidewinderDemo(TextColors.brightWhite on TextColors.brightMagenta).manualTest()

    println("demos.DijkstraDemo...")
    DijkstraDemo().manualTest()

    println("demos.AldousBroderDemo...")
    AldousBroderDemo().manualTest()
}