package yage.base.cuda.driver

import jcuda.nvrtc.JNvrtc.nvrtcCompileProgram
import jcuda.nvrtc.JNvrtc.nvrtcCreateProgram
import jcuda.nvrtc.JNvrtc.nvrtcDestroyProgram
import jcuda.nvrtc.JNvrtc.nvrtcGetCUBIN
import jcuda.nvrtc.JNvrtc.nvrtcGetCUBINSize
import jcuda.nvrtc.JNvrtc.nvrtcGetPTX
import jcuda.nvrtc.JNvrtc.nvrtcGetProgramLog
import jcuda.nvrtc.nvrtcProgram

// TODO: headers, includes

class CuProgram(val src: String, val name: String) extends CuObject[nvrtcProgram]:

  def this(src: String) = this(src, null)

  override def create() =
    val res = nvrtcProgram()
    nvrtcCreateProgram(res, src, name, 0, null, null)
    res

  // return CuCompilationResult
  def compile(options: Array[String] = null) =
    val size = if options == null then 0 else options.size
    nvrtcCompileProgram(handle, size, options)

  def getCompilationLog() =
    val pRes = Array("")
    nvrtcGetProgramLog(handle, pRes)
    pRes(0)

  def getPtx() =
    val pRes = Array("")
    nvrtcGetPTX(handle, pRes)
    pRes(0)

  def getCubin() =
    val pSize = Array(0l)
    nvrtcGetCUBINSize(handle, pSize)
    val res = new Array[Byte](pSize(0).toInt)
    nvrtcGetCUBIN(handle, res)
    res

  override def destroy() =
    nvrtcDestroyProgram(handle)