package test.base.nanovg.pure

import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.*

import java.io.File

object Test:

  def main(args: Array[String]) =
    val a = 3.1457f
    val s = f"$a%.2f"
    println(s)
