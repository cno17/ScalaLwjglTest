package test.base.opengl.resource

import yage.base.opengl.resource.Range2

object RangeTest:

  def main(args: Array[String]) =
    val r = Range2(256, 256)
    println(r.ex)
