package yage.base.par

import org.joml.Vector3f
import org.lwjgl.util.par.ParShapes.par_shapes__compute_welded_normals
import org.lwjgl.util.par.ParShapes.par_shapes_compute_aabb
import org.lwjgl.util.par.ParShapes.par_shapes_compute_normals
import org.lwjgl.util.par.ParShapes.par_shapes_create_cone
import org.lwjgl.util.par.ParShapes.par_shapes_create_disk
import org.lwjgl.util.par.ParShapes.par_shapes_create_dodecahedron
import org.lwjgl.util.par.ParShapes.par_shapes_create_klein_bottle
import org.lwjgl.util.par.ParShapes.par_shapes_create_plane
import org.lwjgl.util.par.ParShapes.par_shapes_create_trefoil_knot
import org.lwjgl.util.par.ParShapes.par_shapes_free_mesh
import org.lwjgl.util.par.ParShapes.par_shapes_merge
import org.lwjgl.util.par.ParShapesMesh
import yage.shape.ABox3

object TriMesh:

  def cone(slices: Int, stacks: Int) =
    TriMesh(par_shapes_create_cone(slices, stacks))

  def disc(slices: Int, radius: Float, center: Vector3f, normal: Vector3f) =
    val c = Array(center.x, center.y, center.z)
    val n = Array(normal.x, normal.y, normal.z)
    TriMesh(par_shapes_create_disk(radius, slices, c, n))

  def dodecahedron() =
    TriMesh(par_shapes_create_dodecahedron())

  def kleinBottle(slices: Int, stacks: Int) =
    TriMesh(par_shapes_create_klein_bottle(slices, stacks))
  
  def plane(slices: Int, stacks: Int) =
    TriMesh(par_shapes_create_plane(slices, stacks))

  def trefoilKnot(slices: Int, stacks: Int, radius: Float) =
    TriMesh(par_shapes_create_trefoil_knot(slices, stacks, radius))

// All meshes must have positions, normals and texture coordinates.
// Tangents can be created later

class TriMesh(val delegate: ParShapesMesh) extends TriMeshTransformer {
  
  val numV = delegate.npoints()
  val numT = delegate.ntriangles()

  val posB = delegate.points(3 * numV)
  val norB = delegate.normals(3 * numV)
  val tecB = delegate.tcoords(2 * numV)
  val indB = delegate.triangles(3 * numT)

  val box = ABox3()

  updateBox()

  def computeWeldedNormals() = 
    par_shapes__compute_welded_normals(delegate)
    this
    
  def computeNormals() = 
    par_shapes_compute_normals(delegate)
    this
    
  def toObj() = 0

  def merge(m: TriMesh) =
    par_shapes_merge(delegate, m.delegate)
    this

  def updateBox() =
    val a = new Array[Float](6)
    par_shapes_compute_aabb(delegate, a)
    box.min.set(a(0), a(1), a(2))
    box.max.set(a(3), a(4), a(5))
    this

  def destroy() =
    par_shapes_free_mesh(delegate)
}
