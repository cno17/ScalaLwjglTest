package test.base.opengl.reflection

import org.lwjgl.BufferUtils
import yage.base.opengl.shader.program.Program
import yage.base.opengl.shader.program.resource.Block.Type.Storage
import yage.base.glfw.GlApp
import yage.scene.light.PointLight

import java.io.File

object StorageBlockTest extends GlApp:

  override def init() =
    val nl = 2
    val bc = PointLight.byteCount
    val bb = BufferUtils.createByteBuffer(nl * bc)
    for i <- 0 to nl - 1 do
      PointLight() >> (bb, i * bc)
    val p = Program(srcFile("StorageBlockTest2.vert"))
    for b <- p.blocks(Storage) do
      for m <- b.members do
        println(m)
    close()

