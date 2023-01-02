package demos

import com.github.nwillc.ksvg.RenderMode
import com.github.nwillc.ksvg.elements.Container
import com.github.nwillc.ksvg.elements.SVG
import java.io.FileWriter

class SVGDemo {
    fun codeMonkey() {
        val svg = SVG.svg(true) {
            height = "300"
            width = "300"
            style {
                body = """

                 svg .black-stroke { stroke: black; stroke-width: 2; }
                 svg .fur-color { fill: white; }

             """.trimIndent()
            }
            // Label
            text {
                x = "40"
                y = "50"
                body = "#CODE"
                fontFamily = "monospace"
                fontSize = "40px"
            }
            // Ears - example using a function because USE tag doesn't work in Safari
            ear(100, 100)
            ear(240, 70)
            // Face
            circle {
                cssClass = "black-stroke"
                id = "face"
                cx = "180"
                cy = "140"
                r = "80"
                fill = "#aa450f"
            }
            // Eyes
            circle {
                cssClass = "black-stroke fur-color"
                id = "eye"
                cx = "160"
                cy = "95"
                r = "20"
            }
            use {
                x = "45"
                y = "-5"
                href = "#eye"
            }
            // Muzzle
            circle {
                cssClass = "black-stroke fur-color"
                cx = "195"
                cy = "178"
                r = "65"
            }
            // Nostrils
            circle {
                id = "nostril"
                cx = "178"
                cy = "138"
                r = "4"
                fill = "black"
            }
            use {
                x = "35"
                y = "-5"
                href = "#nostril"
            }
            // Mouth
            path {
                cssClass = "black-stroke"
                d = "M 150 150 C 100,250 305,260 230,140 C 205,190 165,170 150,150 Z"
                fill = "red"
            }
        }

        FileWriter("codeMonkey.svg").use {
            svg.render(it, RenderMode.FILE)
        }
    }

    private fun Container.ear(x: Int, y: Int) {
        circle {
            cssClass = "black-stroke fur-color"
            cx = x.toString()
            cy = y.toString()
            r = "40"
        }
        circle {
            cssClass = "black-stroke fur-color"
            cx = x.toString()
            cy = y.toString()
            r = "28"
        }
    }
}

fun main() {
    SVGDemo().codeMonkey()
}