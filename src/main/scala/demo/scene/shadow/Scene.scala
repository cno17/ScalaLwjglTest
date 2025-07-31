package demo.scene.shadow

import org.joml.Vector3f

object Scene:
  
  val boxes = Array(
    /* Walls */
    Vector3f(-5.0f, -0.1f, -5.0f),
    Vector3f(5.0f, 0.0f, 5.0f),
    Vector3f(-0.5f, 0.0f, -0.5f),
    Vector3f(0.5f, 1.0f, 0.5f),
    Vector3f(-5.1f, 0.0f, -5.0f),
    Vector3f(-5.0f, 5.0f, 5.0f),
    Vector3f(5.0f, 0.0f, -5.0f),
    Vector3f(5.1f, 5.0f, 5.0f),
    Vector3f(-5.0f, 0.0f, -5.1f),
    Vector3f(5.0f, 5.0f, -5.0f),
    Vector3f(-5.0f, 0.0f, 5.0f),
    Vector3f(5.0f, 5.0f, 5.1f),
    Vector3f(-5.0f, 5.0f, -5.0f),
    Vector3f(5.0f, 5.1f, 5.0f),
    /* Boxes */
    Vector3f(-2.5f, 0.0f, -1.5f),
    Vector3f(-1.5f, 1.0f, -0.5f),
    Vector3f(-2.5f, 0.0f, 1.5f),
    Vector3f(-1.5f, 1.0f, 2.5f),
    Vector3f(1.5f, 0.0f, 1.5f),
    Vector3f(2.5f, 1.0f, 2.5f),
    Vector3f(1.5f, 0.0f, -2.5f),
    Vector3f(2.5f, 1.0f, -1.5f)
  )

  val boxes2 = Array(
    Vector3f(-5.0f, -0.1f, -5.0f),
    Vector3f(5.0f, 0.0f, 5.0f),
    Vector3f(-0.5f, 0.0f, -0.5f),
    Vector3f(0.5f, 1.0f, 0.5f),
    Vector3f(-2.5f, 0.0f, -1.5f),
    Vector3f(-1.5f, 1.0f, -0.5f),
    Vector3f(-2.5f, 0.0f, 1.5f),
    Vector3f(-1.5f, 1.0f, 2.5f),
    Vector3f(1.5f, 0.0f, 1.5f),
    Vector3f(2.5f, 1.0f, 2.5f),
    Vector3f(1.5f, 0.0f, -2.5f),
    Vector3f(2.5f, 1.0f, -1.5f)
  )

  val boxes3 = Array(
    Vector3f(-25.0f, -0.1f, -25.0f),
    Vector3f(25.0f, 0.0f, 25.0f),
    Vector3f(-2.5f, 0.0f, -1.5f),
    Vector3f(-1.5f, 1.0f, -0.5f),
    Vector3f(-2.5f, 0.0f, 1.5f),
    Vector3f(-1.5f, 1.0f, 2.5f),
    Vector3f(1.5f, 0.0f, 1.5f),
    Vector3f(2.5f, 1.0f, 2.5f),
    Vector3f(1.5f, 0.0f, -2.5f),
    Vector3f(2.5f, 1.0f, -1.5f)
  )
