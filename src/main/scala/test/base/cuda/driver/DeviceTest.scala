package test.cuda.driver

import jcuda.*
import jcuda.driver.CUdevice
import jcuda.driver.CUdevice_attribute.CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MAJOR
import jcuda.driver.CUdevice_attribute.CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MINOR
import jcuda.driver.CUresult
import jcuda.driver.JCudaDriver.cuCtxGetDevice
import jcuda.driver.JCudaDriver.cuDeviceGetAttribute


object DeviceTest:

  def main(args: Array[String]) =
    val device = CUdevice()
    val status = cuCtxGetDevice(device)
    if status != CUresult.CUDA_SUCCESS then throw RuntimeException(CUresult.stringFor(status))
    val majorArray = Array(0)
    val minorArray = Array(0)
    cuDeviceGetAttribute(majorArray, CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MAJOR, device)
    cuDeviceGetAttribute(minorArray, CU_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY_MINOR, device)
    val major = majorArray(0)
    val minor = minorArray(0)
    // return major * 10 + minor
    println("ok")