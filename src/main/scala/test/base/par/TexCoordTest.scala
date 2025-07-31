package test.base.par

import org.lwjgl.util.par.ParShapes.*
import org.lwjgl.util.par.ParShapesMesh

// All theese meshes have texture coordinates!

object TexCoordTest:

  def main(args: Array[String]) =
    var mesh: ParShapesMesh = null
    mesh = par_shapes_create_cone(20, 20)
    mesh = par_shapes_create_klein_bottle(20, 20)
    mesh = par_shapes_create_hemisphere(20, 20)
    mesh = par_shapes_create_parametric_disk(10, 10)
    mesh = par_shapes_create_parametric_sphere(10, 10)
    mesh = par_shapes_create_torus(20, 20, 0.5f)
    mesh = par_shapes_create_trefoil_knot(20, 20, 5)
    val np = mesh.npoints()
    val nt = mesh.ntriangles()
    val posB = mesh.points(3 * np)
    val texB = mesh.tcoords(2 * np)
    val indB = mesh.triangles(3 * nt)
    println(posB)
    println(texB)
    println(indB)
    println(np)
    println(nt)
