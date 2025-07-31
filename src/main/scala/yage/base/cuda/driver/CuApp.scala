package yage.base.cuda.driver

import jcuda.driver.JCudaDriver
import jcuda.driver.JCudaDriver.cuInit
import jcuda.nvrtc.JNvrtc
import yage.base.cuda.FileExt

import java.io.File

trait CuApp extends FileExt:

  var device: CuDevice = null
  var context: CuContext = null

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

  def main(args: Array[String]) =
    JCudaDriver.setExceptionsEnabled(true)
    JNvrtc.setExceptionsEnabled(true)
    // more
    cuInit(0)
    device = CuDevice(0)
    context = CuContext(device)
    initialize()
    execute()
    terminate()
