package yage.scene.camera.example

// Rotates a point on a circle/arc to reach a target angle using the rotation
// direction with the shortest distance on the circle.
// Angles are specified in radians.

class ArcRotor:

  var maxAcceleration: Double = Math.toRadians(250.0f)
  var maxDeceleration: Double = Math.toRadians(250.0f)
  var current: Double = 0.0
  var target: Double = 0.0
  var velocity: Double = 0.0

  def update(elapsedTimeInSeconds: Float): Unit =
    if current == target then return
    var currentToTarget = Math.PI - Math.abs((Math.abs(current - target) % (2.0 * Math.PI)) - Math.PI)
    if (current - target + 2.0 * Math.PI) % (2.0 * Math.PI) < Math.PI then
      currentToTarget *= -1.0
    val directStopDistance = (velocity * velocity) / (2.0f * maxDeceleration)
    var acceleration = 0.0
    if (velocity * currentToTarget > 0.0f && directStopDistance >= Math.abs(currentToTarget)) then
      /* Decelerate */
      val directDec = maxDeceleration
      acceleration = (if currentToTarget < 0.0 then -1 else 1) * -directDec
    else
      /* Accelerate */
      val directAcc = maxAcceleration
      acceleration = (if currentToTarget < 0.0 then -1 else 1) * directAcc

    velocity += acceleration * elapsedTimeInSeconds
    val way = velocity * elapsedTimeInSeconds
    if velocity * currentToTarget > 0.0f && Math.abs(way) > Math.abs(currentToTarget) then
      /* We would move too far */
      velocity = 0.0
      current = target
    else
      current = (current + way + 2.0 * Math.PI) % (2.0 * Math.PI)

