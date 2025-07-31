package yage.scene.camera.example

class ScalarMover

    var maxAcceleration = 200.0
    var maxDeceleration = 200.0
    var current = 0.0
    var target = 0.0
    var velocity = 0.0

    def update(elapsedTimeInSeconds: Float): Unit =
        if current == target then return
        val currentToTarget = target - current
        val directStopDistance = (velocity * velocity) / (2.0 * maxDeceleration)
        var acceleration = 0.0
        if velocity * currentToTarget > 0.0 && directStopDistance >= Math.abs(currentToTarget) then
            // decelerate
            val directDec = maxDeceleration
            acceleration = (if currentToTarget < 0.0 then -1 else 1) * -directDec
        else 
            // accelerate
            val directAcc = maxAcceleration
            acceleration = (if currentToTarget < 0.0 then -1 else 1) * +directAcc
        
        velocity += acceleration * elapsedTimeInSeconds
        val way = velocity * elapsedTimeInSeconds
        if velocity * currentToTarget > 0.0 && Math.abs(way) > Math.abs(currentToTarget) then
            // we would move too far
            velocity = 0.0
            current = target
        else 
            current += way

