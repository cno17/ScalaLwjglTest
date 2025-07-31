package test.cuda.runtime

import jcuda.Pointer
import jcuda.jcublas.JCublas2.cublasCreate
import jcuda.jcublas.JCublas2.cublasDestroy
import jcuda.jcublas.JCublas2.cublasSscal
import jcuda.jcublas.cublasHandle
import jcuda.runtime.JCuda
import jcuda.runtime.JCuda.cudaDeviceSynchronize
import jcuda.runtime.JCuda.cudaFreeHost
import jcuda.runtime.JCuda.cudaHostAlloc
import jcuda.runtime.JCuda.cudaHostGetDevicePointer
import jcuda.runtime.JCuda.cudaSetDeviceFlags
import yage.base.cuda.runtime.CudaApp
import yage.base.cuda.runtime.CudaDevice

import java.nio.ByteOrder

/**
 * An example showing how to use mapped memory in JCuda. Host memory is
 * allocated and mapped to the device. There, it is modified with a
 * runtime library function (CUBLAS, for example), which then
 * effectively writes to host memory.
 */


object MappedMemory extends CudaApp:

  override def execute() =
    val device = CudaDevice(0)
    if device.props.canMapHostMemory == 0 then throw Exception("bad luck")
    // Set the flag indicating that mapped memory will be used
    // todo how to wrap this?
    // device.activate(flags)!
    cudaSetDeviceFlags(JCuda.cudaDeviceMapHost)
    device.activate()

    //

    // Allocate mappable host memory
    val n = 5
    val hostPointer = Pointer()
    cudaHostAlloc(hostPointer, n * 4, JCuda.cudaHostAllocMapped)

    // Create a device pointer mapping the host memory
    val devicePointer = Pointer()
    cudaHostGetDevicePointer(devicePointer, hostPointer, 0)

    // Obtain a ByteBuffer for accessing the data in the host pointer. 
    // Modifications in this ByteBuffer will be visible in the device memory.
    val byteBuffer = hostPointer.getByteBuffer(0, n * 4)
    // Set the byte order of the ByteBuffer
    byteBuffer.order(ByteOrder.nativeOrder())

    // For convenience, view the ByteBuffer as a FloatBuffer and fill it with some sample data
    val floatBuffer = byteBuffer.asFloatBuffer()
    print("Input : ")
    for i <- 0 to n - 1 do
      floatBuffer.put(i, i.toFloat)
      print(floatBuffer.get(i) + ", ")
    println()

    // Apply a CUBLAS routine to the device pointer. 
    // This will modify the host data, which was mapped to the device.
    val handle = cublasHandle()
    cublasCreate(handle)
    val two = Pointer.to(Array(2f))
    cublasSscal(handle, n, two, devicePointer, 1)
    cublasDestroy(handle)
    cudaDeviceSynchronize()

    // Print the contents of the host memory after the modification via the mapped pointer.
    print("Output: ")
    for i <- 0 to n - 1 do
      print(floatBuffer.get(i) + ", ")
    println()

    // Clean up
    cudaFreeHost(hostPointer)

