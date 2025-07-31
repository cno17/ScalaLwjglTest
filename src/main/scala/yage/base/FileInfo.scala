package yage.base

import java.io.File

trait FileInfo:

  def srcDir =
    val p = getClass.getPackage.getName.replace('.', '\\')
    File(getClass.getClassLoader.getResource(p).toURI)

  def resDir =
    val p = "."
    File(getClass.getClassLoader.getResource(p).toURI)

  def srcFile(path: String) = File(srcDir, path)

  def resFile(path: String) = File(resDir, path)
