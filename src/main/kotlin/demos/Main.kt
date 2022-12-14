package demos

fun main(_args: Array<String>) {

    val demos = listOf(
        BinaryTreeDemo(),
        SidewinderDemo(),
        DijkstraDemo(),
        AldousBroderDemo(),
        WilsonsDemo(),
        HuntAndKillDemo(),
        RecursiveBacktrackerDemo(),
        DeadEndCellCount(),
        KillingCellsDemo(),
        SimpleMaskDemo()
    )

    demos.forEach { demo ->
        println("${demo::class.simpleName}...")
        demo.run()
    }
}