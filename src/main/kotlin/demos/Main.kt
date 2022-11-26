package demos

import com.github.ajalt.mordant.rendering.TextColors

fun main(_args: Array<String>) {
    println("demos.BinaryTreeDemo...")
    BinaryTreeDemo(TextColors.brightWhite).manualTest()

    println("demos.SidewinderDemo...")
    SidewinderDemo(TextColors.brightWhite).manualTest()

    println("demos.DijkstraDemo...")
    DijkstraDemo(TextColors.brightWhite).manualTest()

    println("demos.AldousBroderDemo...")
    AldousBroderDemo(TextColors.brightWhite).run()

    println("demos.WilsonsDemo...")
    WilsonsDemo().run()
}