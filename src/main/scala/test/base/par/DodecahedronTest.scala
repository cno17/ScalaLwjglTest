package test.base.par

import org.lwjgl.util.par.ParShapes.*
import org.lwjgl.util.par.ParShapesMesh

object DodecahedronTest:

  def main(args: Array[String]) =
    // val mesh = par_shapes_create_dodecahedron()
    val mesh = par_shapes_create_tetrahedron()
    val nv = mesh.npoints()
    val nt = mesh.ntriangles()
    val posB = mesh.points(3 * nv)
    val indB = mesh.triangles(3 * nt)
    println(nv)
    println(nt)
    println(posB.capacity())
    // println(indB.capacity())
    for i <- 0 to posB.capacity() - 1 do println(posB.get(i))
    for i <- 0 to indB.capacity() - 1 do println(indB.get(i))

    // mesh: translate, rotate, scale, export as obj
    // mesh: compute abox, normals

    // class ParShapeMesh