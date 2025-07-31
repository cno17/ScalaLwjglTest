package demo.base.learn.util

import org.joml.Matrix4f

class Camera:

  // posV = matMV * posM
  
  val matVW = Matrix4f()
  val matWV = Matrix4f()
  val matVC = Matrix4f()
  val matCV = Matrix4f()
  val matWC = Matrix4f()
  val matCW = Matrix4f()

  def traX(t: Float) = matVW.translate(t, 0f, 0f)
  def traY(t: Float) = matVW.translate(0f, t, 0f)
  def traZ(t: Float) = matVW.translate(0f, 0f, t)
  def rotX(r: Float) = matVW.rotateX(r)
  def rotY(r: Float) = matVW.rotateY(r)
  def rotZ(r: Float) = matVW.rotateZ(r)
  
  // can be called in every frame because there are not too many cameras in a game
  def update() =
    matWV.set(matVW).invert()
    matCV.set(matVC).invert()
    matWC.set(matVC).mul(matWV)
    matCW.set(matWC).invert()
