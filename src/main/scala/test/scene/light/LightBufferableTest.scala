package test.scene.light

import org.lwjgl.BufferUtils
import yage.scene.light.PointLight

object LightBufferableTest:

  def main(args: Array[String]) =
    val bb = BufferUtils.createByteBuffer(1024)
    val l1 = PointLight()
    l1.diffuseColor.set(0.2f, 0.3f, 0.5f)
    l1.posV.set(2, 3, 5)
    l1 >> (bb, 64)
    val l2 = PointLight()
    println(l2)
    l2 << (bb, 64)
    println(l2)