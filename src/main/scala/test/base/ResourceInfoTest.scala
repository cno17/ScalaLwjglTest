package test.base

import yage.base.FileInfo

object ResourceInfoTest extends FileInfo:

  def main(args: Array[String]) =
    val f = resFile("Shaders/Color.vert")
    println(f.getAbsolutePath)
    println(f.exists())
