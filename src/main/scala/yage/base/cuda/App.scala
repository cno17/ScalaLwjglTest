package yage.base.cuda

import java.io.File

// Should become base class of CuApp and CudaApp

trait App extends FileExt:

  def initialize() = {}
  def execute() = {}
  def terminate() = {}

  def srcDir =
    val path = getClass.getPackage.getName.replace('.', '\\')
    File(getClass.getClassLoader.getResource(path).toURI)

  def resDir =
    File(getClass.getClassLoader.getResource(".").toURI)

  def srcFile(path: String) = File(srcDir, path)

  def resFile(path: String) = File(resDir, path)
