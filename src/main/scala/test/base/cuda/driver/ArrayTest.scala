package test.cuda.driver

import jcuda.*
import jcuda.driver.CUDA_ARRAY_DESCRIPTOR
import jcuda.driver.CUarray
import jcuda.driver.CUarray_format
import jcuda.driver.CUcontext
import jcuda.driver.CUdevice
import jcuda.driver.JCudaDriver
import jcuda.driver.JCudaDriver.cuArrayCreate
import jcuda.driver.JCudaDriver.cuCtxCreate
import jcuda.driver.JCudaDriver.cuDeviceGet
import jcuda.driver.JCudaDriver.cuInit
import yage.base.cuda.driver.CuArrayFormat.F32
import yage.base.cuda.driver.CuContext
import yage.base.cuda.driver.CuDevice
import yage.base.cuda.driver.memory.CuArray1D

object ArrayTest:

  def main(args: Array[String]) =
    JCudaDriver.setExceptionsEnabled(true)
    cuInit(0)
    createArray1()

  def createArray1() =
    val context = CUcontext()
    val device = CUdevice()
    cuDeviceGet(device, 0)
    cuCtxCreate(context, 0, device)
    val a = CUarray()
    val ad = CUDA_ARRAY_DESCRIPTOR()
    ad.Width = 5
    ad.Height = 0
    ad.NumChannels = 1
    ad.Format = CUarray_format.CU_AD_FORMAT_FLOAT
    cuArrayCreate(a, ad)
    println("ok")

  def createArray2() =
    val d = CuDevice(0)
    val c = CuContext(d)
    val a = CuArray1D(5, 1, F32)
    println("ok")