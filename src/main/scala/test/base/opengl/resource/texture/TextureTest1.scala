package test.base.opengl.resource.texture

import org.lwjgl.stb.STBImage.stbi_load
import org.lwjgl.system.MemoryStack
import yage.base.glfw.GlApp

object TextureTest1 extends GlApp:

  override def init() =
    val f1 = resFile("Images/Bird1.jpg")
    val f2 = resFile("Images/Grass.jpg")
    val stack = MemoryStack.stackPush()
    val psi = stack.ints(0)
    val psj = stack.ints(0)
    val pnc = stack.ints(0)
    val data = stbi_load(f1.getAbsolutePath(), psi, psj, pnc, 4)
    val si = psi.get(0)
    val sj = psj.get(0)
    val nc = pnc.get(0)
    MemoryStack.stackPop()
    // for i <- 0 to 100 do println(data.get(i))
    println(si)
    println(sj)
    println(si * sj * 4)
    println(data.capacity())
    close()
