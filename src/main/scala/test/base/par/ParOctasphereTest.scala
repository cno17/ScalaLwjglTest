package test.base.par

import org.lwjgl.BufferUtils
import org.lwjgl.util.par.ParOctasphere.par_octasphere_get_counts
import org.lwjgl.util.par.ParOctasphere.par_octasphere_populate
import org.lwjgl.util.par.ParOctasphereConfig
import org.lwjgl.util.par.ParOctasphereMesh

// rounded cuboids!
// https://prideout.net/blog/octasphere/

object ParOctasphereTest:

  def main(args: Array[String]) =

    val cfg = ParOctasphereConfig.create()
    cfg.corner_radius(5)
    cfg.width(100)
    cfg.height(100)
    cfg.depth(20)
    cfg.num_subdivisions(0)

    val pNumI = BufferUtils.createIntBuffer(1)
    val pNumV = BufferUtils.createIntBuffer(1)
    par_octasphere_get_counts(cfg, pNumI, pNumV)
    val numI = pNumI.get
    val numV = pNumV.get

    println(numI)
    println(numV)

    val pIndices = BufferUtils.createShortBuffer(numI)
    val pPositions = BufferUtils.createFloatBuffer(3 * numV)
    val pNormals = BufferUtils.createFloatBuffer(3 * numV)
    val pTexCoords = BufferUtils.createFloatBuffer(2 * numV)

    val mesh = ParOctasphereMesh.create()
    mesh.indices(pIndices)
    mesh.positions(pPositions)
    mesh.normals(pNormals)
    mesh.texcoords(pTexCoords)

    par_octasphere_populate(cfg, mesh)

    for i <- 0 to 3 * numV - 1 do
      val pPos = mesh.positions(mesh.num_vertices() * 3)
      println(pPos.get(i))
      // println(pPositions.get(i))

