package test.base

import yage.base.FileInfo

object FileInfoTest extends FileInfo:

  def main(args: Array[String]) =
    println(srcDir.getAbsolutePath)