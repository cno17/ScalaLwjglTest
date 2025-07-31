package test.base.par

import org.lwjgl.BufferUtils
import org.lwjgl.util.par.ParSLConfig
import org.lwjgl.util.par.ParSLPosition
import org.lwjgl.util.par.ParSLSpineList
import org.lwjgl.util.par.ParStreamlines.parsl_create_context
import org.lwjgl.util.par.ParStreamlines.parsl_destroy_context
import org.lwjgl.util.par.ParStreamlines.parsl_mesh_from_lines

object ParStreamlinesTest:

  def main(args: Array[String]): Unit =

    val cfg = ParSLConfig.create()
    val ctx = parsl_create_context(cfg)

    val vertices = ParSLPosition.create(5)
    vertices.get(0).set(1, 1)
    vertices.get(1).set(2, 1)
    vertices.get(2).set(3, 1)
    vertices.get(3).set(2, 2)
    vertices.get(4).set(2, 3)

    val lengths = BufferUtils.createShortBuffer(2)
    lengths.put(3.toShort)
    lengths.put(2.toShort)
    lengths.rewind()

    val lines = ParSLSpineList.create()
    lines.vertices(vertices)
    lines.spine_lengths(lengths)

    val mesh = parsl_mesh_from_lines(ctx, lines)
    println(mesh.num_vertices())
    println(mesh.num_triangles())

    parsl_destroy_context(ctx)


