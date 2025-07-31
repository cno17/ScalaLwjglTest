package test.scene

import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f

class Picking:

  val width = 800f // viewport?
  val height = 600f
  val viewMatrix = Matrix4f()
  val projectionMatrix = Matrix4f()

  private def calculateRay(mouseX: Float, mouseY: Float) =
    val deviceCoords = getNormalizedDeviceCoordinates(mouseX, mouseY)
    // println(deviceCoords.x+", "+deviceCoords.y);
    val clipCoords = Vector4f(deviceCoords.x, deviceCoords.y, -1f, 1f)
    val eyeCoords = toEyeCoords(clipCoords)
    val worldRay = toWorldCoords(eyeCoords)
    worldRay

  private def toWorldCoords(eyeCoords: Vector4f) =
    val invertedView = viewMatrix.invert()
    val rayWorld = invertedView.transform(eyeCoords)
    val mouseRay = Vector3f(rayWorld.x, rayWorld.y, rayWorld.z)
    mouseRay.normalize()
    mouseRay

  private def toEyeCoords(clipCoords: Vector4f) =
    val invertedProjection = projectionMatrix.invert()
    val eyeCoords = invertedProjection.transform(clipCoords)
    Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f)

  private def getNormalizedDeviceCoordinates(mouseX: Float, mouseY: Float) =
    val x = (2f * mouseX) / width - 1f
    val y = (2f * mouseY) / height - 1f
    Vector2f(x, -y)
