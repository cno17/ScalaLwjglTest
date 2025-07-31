package yage.scene.camera.example

import org.joml.Vector3f
import Vector3Mover.SMALL_VALUE_THRESHOLD

/**
 * This is an integrator providing smooth convergence of a current
 * position to a target position in 3D space, based on velocity and
 * acceleration computations.
 *
 *
 * Initially, the current and the target position is zero and no velocity or
 * acceleration is applied. Once, the user sets the [.target] to some
 * value, the [.current] value will begin to converge against that target
 * using time and maximum acceleration constraints.
 *
 *
 * To advance the integration, the client invokes [.update] with
 * the elapsed time in seconds since the last call to update.
 *
 *
 * This class does not provide tweening, which is a parameterization of a
 * defction between two given points. It instead uses a simulation based on
 * velocity and acceleration and allowing to alter the [.target] to any
 * value at any time.
 *
 * @author Kai Burjack
 */

object Vector3Mover:
    val SMALL_VALUE_THRESHOLD: Float = 1E-5f

class Vector3Mover:

    // the maximum acceleration directly towards the target.
    var maxDirectAcceleration: Float = 20.0f

    // The maximum deceleration directly towards the target.
    var maxDirectDeceleration: Float = 100.0f

    // The maximum deceleration (in positive values) towards the velocity
    // component perpendicular to the target direction.
    var maxPerpendicularDeceleration: Float = 30.0f

    // The current position. This will change after an invocation to update()
    val current: Vector3f = Vector3f()

    // The desired target position. Set this to any value at any time.
    val target: Vector3f = Vector3f()

    // The current acceleration. MUST NOT be modified from outside.
    val acceleration: Vector3f = Vector3f()

    // The current velocity. MUST NOT be modified from outside.
    val velocity: Vector3f = Vector3f()

    // Some helper objects. JOML did not use any, but joml-camera now has to.
    private val currentToTarget: Vector3f = Vector3f()
    private val currentToTargetNormalized: Vector3f = Vector3f()
    private val perpendicularVelocityComponent: Vector3f = Vector3f()
    private val directVelocityComponent: Vector3f = Vector3f()
    private val directAcceleration: Vector3f = Vector3f()
    private val perpendicularAcceleration: Vector3f = Vector3f()
    private val newAcceleration: Vector3f = Vector3f()
    private val newVelocity: Vector3f = Vector3f()
    private val way: Vector3f = Vector3f()

    // Update the simulation based on the elapsed time since the last update.
    def update(elapsedTimeInSeconds: Float): Unit =
        // Compute the way we need to go
        currentToTarget.set(target).sub(current)
        if currentToTarget.length() < 1E-5 then return
        currentToTargetNormalized.set(currentToTarget).normalize()
        // Dot product in order to project the velocity onto the target direction.
        val dot = currentToTargetNormalized.dot(velocity)
        // Compute the perpendicular velocity component (how much of the current
        // velocity is directed exactly perpendicular to the target).
        perpendicularVelocityComponent.set(currentToTargetNormalized)
        perpendicularVelocityComponent.mul(dot)
        perpendicularVelocityComponent.sub(velocity)
        // Now this contains the vector to eliminate the perpendicular component,
        // i.e. it is directed towards the line of sight between the target and current.

        // Compute the direct velocity component (how much of the current
        // velocity is directed towards the target).
        directVelocityComponent.set(currentToTargetNormalized)
        directVelocityComponent.mul(Math.abs(dot))

        // In which time can we reach complete zero perpendicular movement
        val timeToStopPerpendicular: Float = perpendicularVelocityComponent.length() / maxPerpendicularDeceleration

        /*
         * This is how long our whole movement to the target needs to take at
         * least in order for the perpendicular movement to stop (which we
         * want!). The problem now is that the length of the direct way depends
         * on the perpendicular movement. The more we move in the perpendicular
         * direction, the longer the direct path becomes.
         */

        /*
         * Compute how far we would move along the direct component if we
         * completely eliminate this velocity component.
         */
        val directStopDistance: Float = directVelocityComponent.lengthSquared() / (2.0f * maxDirectDeceleration)
        /*
         * Now see how much time it will take us to fully stop the direct
         * movement.
         */
        val timeToStopDirect: Float = directVelocityComponent.length() / maxDirectDeceleration

        /*
         * Check if we need to decelerate the direct component, because we would
         * move too far if we didn't.
         */
        if dot >= SMALL_VALUE_THRESHOLD
            && (directStopDistance >= currentToTarget.length() || timeToStopPerpendicular > timeToStopDirect)
        then
            /* We need to decelerate the direct component */
            directAcceleration.set(currentToTargetNormalized).mul(maxDirectDeceleration).negate()
        else 
            /*
             * We can still accelerate directly towards the target. Compute the
             * necessary acceleration to reach the target in the elapsed time.
             */
            val neededDirectAcc: Float = currentToTarget.length() / elapsedTimeInSeconds
            var directAcc = neededDirectAcc
            /* Check if that would be too much acceleration */
            if neededDirectAcc > maxDirectAcceleration then
                /* Limit to maximum allowed acceleration */
                directAcc = maxDirectAcceleration
            
            directAcceleration.set(currentToTargetNormalized).mul(directAcc)

        /*
         * Compute the perpendicular deceleration. If maximum deceleration would
         * be too much for the time, we compute the optimal deceleration based
         * on the elapsed time.
         */
        val neededPerpendicularAcc: Float = perpendicularVelocityComponent.length() / elapsedTimeInSeconds
        var perpendicularDeceleration = neededPerpendicularAcc
        /* Check if that would be too much acceleration */
        if neededPerpendicularAcc > maxPerpendicularDeceleration then
            /* Limit to maximum allowed acceleration */
            perpendicularDeceleration = maxPerpendicularDeceleration
        
        /* If the perpendicular velocity would be too small */
        if (perpendicularVelocityComponent.length() > SMALL_VALUE_THRESHOLD)
            perpendicularAcceleration.set(perpendicularVelocityComponent).normalize().mul(perpendicularDeceleration)
        else 
            perpendicularAcceleration.set(0.0f, 0.0f, 0.0f)

        /* Compute new acceleration */
        newAcceleration.set(directAcceleration).add(perpendicularAcceleration)
        /* Compute new velocity */
        newVelocity.set(newAcceleration).mul(elapsedTimeInSeconds).add(velocity)
        velocity.set(newVelocity)

        way.set(velocity).mul(elapsedTimeInSeconds)
        if way.length() > currentToTarget.length() then 
            velocity.zero()
            way.set(currentToTarget)

        /* Compute new current position based on updated velocity */
        current.add(way)


