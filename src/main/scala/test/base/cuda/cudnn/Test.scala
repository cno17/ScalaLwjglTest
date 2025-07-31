package test.base.cuda.cudnn

import jcuda.jcudnn.JCudnn
import jcuda.runtime.JCuda

object Test:

  def main(args: Array[String]) =
    JCuda.setExceptionsEnabled(true)
    JCudnn.setExceptionsEnabled(true)
    println(2)
