package test.base

import yage.base.opengl.resource.buffer.Buffer.StorageFlag.MapCoherent
import yage.base.opengl.resource.buffer.Buffer.StorageFlag.MapRead
import yage.base.opengl.resource.buffer.Buffer.StorageFlags

object FlagsTest:

  def main(args: Array[String]) =
    val flags = StorageFlags()
    // val flags = StorageFlags(MapRead, MapCoherent)
    println(flags.contains(MapRead))
    println(flags.value)