package test.base.par

import org.lwjgl.util.par.ParShapes.*
import org.lwjgl.util.par.ParShapesMesh

object NormalTest:

  def main(args: Array[String]) =
    val box = new Array[Float](6)
    val mesh = par_shapes_create_dodecahedron()
    par_shapes_compute_normals(mesh)
    par_shapes_compute_aabb(mesh, box)
    val nv = mesh.npoints()
    val nt = mesh.ntriangles()
    val posB = mesh.points(3 * nv)
    val norB = mesh.normals(3 * nv)
    val indB = mesh.triangles(3 * nt)
    println(posB)
    println(norB)
    println(indB)
    println(nv)
    println(nt)
    println(box.mkString("[", ", ", "]"))
    // for i <- 0 to posB.capacity() - 1 do println(posB.get(i))
    // for i <- 0 to indB.capacity() - 1 do println(indB.get(i))

    // mesh: translate, rotate, scale, export as obj
    // mesh: compute abox, normals

    // class ParShapeMesh