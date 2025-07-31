package yage.scene.camera

import org.joml.Vector3f
import yage.shape.Ray3

class SceneView(val index: Int = 0):

  val viewport = Viewport(index)
  val camera = Camera()

  private val ray = new Ray3()
  private val dirC = Vector3f()
  private val dirV = Vector3f()
  private val dirW = Vector3f()

  def pickRayW(mouseX: Float, mouseY: Float) =
    val x = (2 * mouseX) / viewport.w - 1
    val y = (2 * mouseY) / viewport.h - 1
    dirC.set(x, -y, -1f)
    camera.matCV.transformDirection(dirC, dirV)
    dirV.z = -1f
    camera.matVW.transformDirection(dirV, ray.dir)
    camera.matVW.getTranslation(ray.org)
    ray.dir.normalize()
    ray
