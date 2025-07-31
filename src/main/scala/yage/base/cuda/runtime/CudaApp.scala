package yage.base.cuda.runtime

import jcuda.jcublas.JCublas2
import jcuda.jcurand.JCurand
import jcuda.nvrtc.JNvrtc
import jcuda.runtime.JCuda
import yage.base.cuda.FileExt

import java.io.File
import scala.util.Random

trait CudaApp extends FileExt:

  val rng = Random(System.currentTimeMillis())

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

  def rndIntArray(n: Int) =
    val a = new Array[Int](n)
    for i <- 0 to n - 1 do a(i) = rng.nextInt()
    a

  def rndFloatArray(n: Int) =
    val a = new Array[Float](n)
    for i <- 0 to n - 1 do a(i) = rng.nextFloat()
    a

  def main(args: Array[String]) =
    JCuda.setExceptionsEnabled(true)
    JCublas2.setExceptionsEnabled(true)
    JCurand.setExceptionsEnabled(true)
    JNvrtc.setExceptionsEnabled(true)
    // more
    initialize()
    execute()
    terminate()
  