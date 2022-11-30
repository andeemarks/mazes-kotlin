package demos

import com.github.ajalt.mordant.rendering.TextColors

fun main(_args: Array<String>) {
    println("demos.BinaryTreeDemo...")
    BinaryTreeDemo(TextColors.brightWhite).run()

    println("demos.SidewinderDemo...")
    SidewinderDemo(TextColors.brightWhite).run()

    println("demos.DijkstraDemo...")
    DijkstraDemo(TextColors.brightWhite).run()

    println("demos.AldousBroderDemo...")
    AldousBroderDemo(TextColors.brightWhite).run()

    println("demos.WilsonsDemo...")
    WilsonsDemo().run()

    println("demos.HuntAndKillDemo...")
    HuntAndKillDemo().run()

    println("demos.RecursiveBacktrackerDemo...")
    RecursiveBacktrackerDemo().run()

    println("demos.DeadEndCellCount...")
    DeadEndCellCount().run()
}