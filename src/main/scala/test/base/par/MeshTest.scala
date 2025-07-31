package test.base.par

import yage.base.glfw.GlApp
import yage.base.par.TriMesh

object MeshTest extends GlApp:

  override def init() =
    val m = TriMesh.kleinBottle(10, 10)
    println(m.norB)
    println(m.tecB)
    println(m.box)
    m.translate(2, 3, 5)
    println(m.box)
    close()
