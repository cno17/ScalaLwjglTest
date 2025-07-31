package yage.base.cuda.runtime

// base of all classes in the cuda runtime api

abstract class CudaObject[I]:

  var id = create()

  def create(): I
  
  def destroy(): Unit
