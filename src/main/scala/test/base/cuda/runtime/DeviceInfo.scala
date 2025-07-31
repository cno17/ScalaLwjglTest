package test.cuda.runtime

import jcuda.runtime.JCuda.cudaGetDeviceCount
import jcuda.runtime.JCuda.cudaGetDeviceProperties
import jcuda.runtime.cudaDeviceProp
import yage.base.cuda.runtime.CudaDevice
import yage.base.cuda.runtime.CudaApp

object DeviceInfo extends CudaApp:

  override def execute() =
    println(CudaDevice(0).props.toFormattedString())


  def executeOld() =
    val deviceCount = Array(0)
    cudaGetDeviceCount(deviceCount)
    println("Found " + deviceCount(0) + " devices")
    for device <- 0 to deviceCount(0) - 1 do
      println("Properties of device $device:")
      val prop = cudaDeviceProp()
      cudaGetDeviceProperties(prop, device)
      println(prop.toFormattedString())
