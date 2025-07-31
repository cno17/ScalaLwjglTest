package test.base.cuda.driver

import jcuda.driver.CUcontext
import jcuda.driver.CUdevice
import jcuda.driver.CUhostFn
import jcuda.driver.CUstream
import jcuda.driver.JCudaDriver
import jcuda.driver.JCudaDriver.cuCtxCreate
import jcuda.driver.JCudaDriver.cuCtxDestroy
import jcuda.driver.JCudaDriver.cuDeviceGet
import jcuda.driver.JCudaDriver.cuInit
import jcuda.driver.JCudaDriver.cuLaunchHostFunc
import jcuda.driver.JCudaDriver.cuStreamCreate
import jcuda.driver.JCudaDriver.cuStreamSynchronize

/**
 * An example showing how to call a host function via the driver API
 */

object HostFunction:

  def main(args: Array[String]) =
    // Default initialization
    JCudaDriver.setExceptionsEnabled(true)
    cuInit(0)
    val context = CUcontext()
    val device = CUdevice()
    cuDeviceGet(device, 0)
    cuCtxCreate(context, 0, device)
    // Create a stream
    val stream = CUstream()
    cuStreamCreate(stream, 0)
    // Define a host function and launch it
    val fn = HostFn()
    val fn2 = (ud: Object) => println(ud)
    cuLaunchHostFunc(stream, fn, "Example user object")
    // Wait for the stream to finish
    cuStreamSynchronize(stream)
    // Clean up
    cuCtxDestroy(context)
    println("Done")

  // how to use lambda exp?
  class HostFn extends CUhostFn:
    override def call(userData: Object) =
      println(s"Called with \"$userData\"")

