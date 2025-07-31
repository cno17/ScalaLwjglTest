package yage.scene.camera.example

import org.joml.Matrix4f
import org.joml.Quaternionf
import org.joml.Vector3f

// A very simple but fully defctional 6-DOF free/space camera.
// It allows to set the linear acceleration or velocity in world-space and
// the angular acceleration or velocity in local camera/eye space.

class FreeCamera:
  var linearVel: Vector3f = Vector3f()
  var linearAcc: Vector3f = Vector3f()

  /** Always rotation about the local XYZ axes of the camera! */
  var angularVel: Vector3f = Vector3f()
  var angularAcc: Vector3f = Vector3f()

  // linPos, angPos
  var position: Vector3f = Vector3f(0, 0, 10)
  var rotation: Quaternionf = Quaternionf()

  // Update this [FreeCamera] based on the given elapsed time.

  def update(dt: Float) =
    // update linear velocity based on linear acceleration
    linearVel.fma(dt, linearAcc)
    // update angular velocity based on angular acceleration
    angularVel.fma(dt, angularAcc)
    // update the rotation based on the angular velocity
    rotation.integrate(dt, angularVel.x, angularVel.y, angularVel.z)
    // update position based on linear velocity
    position.fma(dt, linearVel)
    this

  // Compute the world-space 'right' vector and store it into `dest`.
  def right(dest: Vector3f) = rotation.positiveX(dest)

  // Compute the world-space 'up' vector and store it into `dest`.
  def up(dest: Vector3f) = rotation.positiveY(dest)

  // Compute the world-space 'forward' vector and store it into `dest`.
  def forward(dest: Vector3f) = rotation.positiveZ(dest).negate()

  /**
   * Apply the camera/view transformation of this [FreeCamera] to the given matrix.
   *
   * @param m
   * the matrix to apply the view transformation to
   * @m
   */
  def apply(m: Matrix4f): Matrix4f =
    m.rotate(rotation).translate(-position.x, -position.y, -position.z)
    
