package test.base.util

import yage.base.FileExt
import yage.base.opengl.shader.Shader.Stage

import java.io.File
import scala.collection.mutable.ArrayBuffer

object FileExtTest2 extends FileExt:

  def main(args: Array[String]) =
    val f = File("diffuse.fra")
    println(stageOf(f))
    println(17)

  def stageOf(f: File) = Stage.values.find(s => s.ext == f.extension).get
