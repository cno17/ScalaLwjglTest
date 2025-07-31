package test.base.opengl

import yage.base.opengl.primitive.Attribute

object Test:

  def main(args: Array[String]) =
    val t = Attribute.Semantics.Color
    println(t)
