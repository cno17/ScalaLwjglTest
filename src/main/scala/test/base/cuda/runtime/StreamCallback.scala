package test.base.cuda.runtime

import jcuda.Pointer
import jcuda.Sizeof
import jcuda.runtime.JCuda
import jcuda.runtime.JCuda.cudaFree
import jcuda.runtime.JCuda.cudaMalloc
import jcuda.runtime.JCuda.cudaMemcpyAsync
import jcuda.runtime.JCuda.cudaStreamAddCallback
import jcuda.runtime.JCuda.cudaStreamCreate
import jcuda.runtime.JCuda.cudaStreamSynchronize
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyHostToDevice
import jcuda.runtime.cudaStreamCallback
import jcuda.runtime.cudaStream_t
import yage.base.cuda.runtime.CudaStream

/**
 * A very basic example for the stream callback functionality
 * in the cuda runtime API.
 */

object StreamCallback:

  def main(args: Array[String]) =
    JCuda.setExceptionsEnabled(true)
    // The stream on which the callbacks will be registered.
    // When this is "null", then it is the default stream.
    val stream = CudaStream()
    println(s"Using stream $stream")
    // Define the callback
    val callback = StreamCallback()

    // Create some dummy data on the host, and copy it to the device asynchronously
    val n = 100000
    val hostData = new Array[Float](n)
    val deviceData = Pointer()
    cudaMalloc(deviceData, n * Sizeof.FLOAT)
    cudaMemcpyAsync(deviceData, Pointer.to(hostData), n * Sizeof.FLOAT, cudaMemcpyHostToDevice, stream.id)
    // Add the callback to the stream that carries the copy operation
    val userData = "Example user data"
    cudaStreamAddCallback(stream.id, callback, userData, 0)
    // Wait until the stream is finished
    cudaStreamSynchronize(stream.id)
    // Clean up
    cudaFree(deviceData)
    println("Done")


  class StreamCallback extends cudaStreamCallback:

    override def call(stream: cudaStream_t, status: Int, userData: Object) =
      println("Callback called")
      println("    stream  : $stream")
      println("    status  : $status")
      println("    userData: $userData")
      println("    thread  : " + Thread.currentThread())
