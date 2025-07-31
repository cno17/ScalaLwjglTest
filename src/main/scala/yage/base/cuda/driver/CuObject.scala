package yage.base.cuda.driver

// base of all classes in the cuda driver api

abstract class CuObject[H]:
  
  val handle = create()

  def create(): H
  def destroy(): Unit