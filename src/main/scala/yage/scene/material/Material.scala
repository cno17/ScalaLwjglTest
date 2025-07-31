package yage.scene.material

import org.joml.Matrix4f
import org.joml.Matrix4x3f
import yage.base.ByteBufferable
import yage.base.opengl.shader.program.Program
import yage.base.joml.VectorExt
import yage.scene.camera.Camera

abstract class Material[M <: Material[M]] extends ByteBufferable[M], VectorExt:
  
  type P <: Program
  
  def program: P
  
  def setModelMatrix(matMW: Matrix4x3f) = this.asInstanceOf[M]
  def setViewMatrix(matWV: Matrix4x3f) = this.asInstanceOf[M]
  def setProjectionMatrix(matVC: Matrix4f) = this.asInstanceOf[M]
  def setModelViewProjectionMatrix(matMC: Matrix4f) = this.asInstanceOf[M]
  
  def setViewAndProjectionMatrix(cam: Camera) = this.asInstanceOf[M]
  
  
  
  
