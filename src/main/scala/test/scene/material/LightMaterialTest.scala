package test.scene.material

import yage.base.opengl.shader.program.resource.Block.Type.Storage
import yage.base.glfw.GlApp
import yage.scene.material.LightMaterial

object LightMaterialTest extends GlApp:

  override def init() =
    val mat = LightMaterial()
    for b <- mat.program.blocks(Storage) do
      println(b.index)
      println(b.name)
      for m <- b.members do
        println("    " + m)
    System.exit(0)


