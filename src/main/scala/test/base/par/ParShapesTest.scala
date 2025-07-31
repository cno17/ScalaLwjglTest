package test.base.par

import org.lwjgl.util.par.ParShapes.*
import org.lwjgl.util.par.ParShapesMesh
object ParShapesTest:

  def main(args: Array[String]) =
    val slices = 10
    val stacks = 10
    val radius = 1f
    var mesh: ParShapesMesh = null
    mesh = par_shapes_create_dodecahedron()
    mesh = par_shapes_create_parametric_sphere(slices, stacks)
    mesh = par_shapes_create_klein_bottle(slices, stacks)
    mesh = par_shapes_create_cylinder(slices, stacks)
    mesh = par_shapes_create_icosahedron()
    mesh = par_shapes_create_cube()
    mesh = par_shapes_create_trefoil_knot(slices, stacks, radius)
    val nv = mesh.npoints()
    val nt = mesh.ntriangles()
    val posB = mesh.points(nv)
    val norB = mesh.normals(nv)
    val tecB = mesh.tcoords(nv)
    val indB = mesh.triangles(3 * nt)
    println(nv)
    println(nt)
    println(s"pos: $posB")
    println(s"nor: $norB")
    println(s"tec: $tecB")
    for i <- 0 to 3 * nt - 1 do
      println(indB.get(i))

    // mesh: translate, rotate, scale, export as obj
    // mesh: compute abox, normals

    // class ParShapeMesh