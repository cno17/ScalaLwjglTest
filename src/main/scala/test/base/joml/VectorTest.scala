package test.base.joml

import org.joml.Vector2f
import yage.base.joml.VectorExt

object VectorTest extends VectorExt:

  def main(args: Array[String]) =
    val v = Vector2f()
    for i <- 1 to 5 do
      println(v.toRandom.length())
