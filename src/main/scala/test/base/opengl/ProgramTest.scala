package test.base.opengl

import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp

import java.io.File

object ProgramLoaderTest extends GlApp:

  override def init() =
    val vf = srcFile("Silhouette.vert")
    val gf = srcFile("Silhouette.geom")
    val ff = srcFile("Silhouette.frag")
    val p = Program(vf, gf, ff)
    println(p)
    close()
