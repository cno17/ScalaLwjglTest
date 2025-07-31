package test.base.cuda.cublas

import jcuda.Pointer
import jcuda.jcublas.JCublas2.cublasCreate
import jcuda.jcublas.JCublas2.cublasDestroy
import jcuda.jcublas.JCublas2.cublasSdot
import jcuda.jcublas.JCublas2.cublasSetPointerMode
import jcuda.jcublas.cublasHandle
import jcuda.jcublas.cublasPointerMode.CUBLAS_POINTER_MODE_DEVICE
import jcuda.jcublas.cublasPointerMode.CUBLAS_POINTER_MODE_HOST
import jcuda.runtime.JCuda.cudaDeviceSynchronize
import jcuda.runtime.JCuda.cudaFree
import jcuda.runtime.JCuda.cudaMalloc
import jcuda.runtime.JCuda.cudaMemcpy
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyDeviceToHost
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyHostToDevice
import yage.base.cuda.runtime.CudaApp

import java.util.Arrays

/**
 * A sample demonstrating the different pointer modes for CUBLAS 2.
 * With CUBLAS 2, functions may receive pointers as arguments which are
 * either used as input parameters or will store results. These pointers
 * may either be pointers to host or to device memory. This sample shows
 * how to obtain the result of a 'dot' operation in host- or device memory.
 */


object PointerModes extends CudaApp:

  override def execute() =

    val n = 100

    // Create the input data: A vector containing the value 1.0 exactly n times.
    val hostData = new Array[Float](n)
    Arrays.fill(hostData, 1.0f)

    // Allocate device memory, and copy the input data to the device
    val deviceData = Pointer()
    cudaMalloc(deviceData, n * 4)
    cudaMemcpy(deviceData, Pointer.to(hostData), n * 4, cudaMemcpyHostToDevice)

    // Create a CUBLAS handle
    val handle = cublasHandle()
    cublasCreate(handle)

    // Execute the 'dot' function in HOST pointer mode:
    // The result will be written to a pointer that points to host memory.
    // Set the pointer mode to HOST
    cublasSetPointerMode(handle, CUBLAS_POINTER_MODE_HOST)

    // Prepare the pointer for the result in HOST memory
    // val hostResult = floatArrayOf(-1.0f)
    val hostResult = Array(-1.0f)
    val hostResultPointer = Pointer.to(hostResult)

    // Execute the 'dot' function
    val beforeHostCall = System.nanoTime()
    cublasSdot(handle, n, deviceData, 1, deviceData, 1, hostResultPointer)
    val afterHostCall = System.nanoTime()

    // Print the result and timing information
    val hostDuration = (afterHostCall - beforeHostCall) / 1e6
    println("Host call duration: $hostDuration ms")
    println("Result: " + hostResult(0))

    // Execute the 'dot' function in DEVICE pointer mode:
    // The result will be written to a pointer that points to device memory.
    // Set the pointer mode to DEVICE
    cublasSetPointerMode(handle, CUBLAS_POINTER_MODE_DEVICE)
    // Prepare the pointer for the result in DEVICE memory
    val deviceResultPointer = Pointer()
    cudaMalloc(deviceResultPointer, 4)
    // Execute the 'dot' defction
    val beforeDeviceCall = System.nanoTime()
    cublasSdot(handle, n, deviceData, 1, deviceData, 1, deviceResultPointer)
    val afterDeviceCall = System.nanoTime()

    // Synchronize in order to wait for the result to be available
    // (note that this is done implicitly when cudaMemcpy is called)
    cudaDeviceSynchronize()
    val afterDeviceSync = System.nanoTime()

    // Copy the result from the device to the host
    val deviceResult = Array(-1.0f)
    cudaMemcpy(Pointer.to(deviceResult), deviceResultPointer, 4, cudaMemcpyDeviceToHost)

    // Print the result and timing information
    val deviceCallDuration = (afterDeviceCall - beforeDeviceCall) / 1e6
    val deviceFullDuration = (afterDeviceSync - beforeDeviceCall) / 1e6
    println("Device call duration: $deviceCallDuration ms")
    println("Device full duration: $deviceFullDuration ms")
    println("Result: " + deviceResult(0))

    // Clean up
    cudaFree(deviceData)
    cublasDestroy(handle)

  println("ok")
