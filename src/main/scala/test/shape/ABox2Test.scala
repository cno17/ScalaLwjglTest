package test.shape

import yage.base.joml.VectorExt
import yage.shape.ABox2
import yage.shape.OBox2

object ABox2Test extends VectorExt:

  def main(args: Array[String]) =
    val ob = OBox2()
    ob.org.set(2, 3)
    ob.dirX.set(+1, 1).normalize()
    ob.dirY.set(-1, 1).normalize()
    ob.radX = 2
    ob.radY = 3
    val ab = ABox2()
    ab.initFrom(ob.vertices)
    println(ab)
