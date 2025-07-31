package test.base.opengl.picking

import demo.base.learn.util.ProgramExt
import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.opengl.GL11C.*
import yage.base.FMath
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.input.Mouse
import yage.base.glfw.window.Window
import yage.scene.primitive.Cuboid
import yage.scene.primitive.Spheroid
import yage.shape.Ray3
import yage.shape.Sphere3

import java.io.File

object PickingDemo1 extends GlApp, ProgramExt:

  val matVW = Matrix4f().translate(0.0f, 2.0f, 20.0f)
  val matWV = Matrix4f(matVW).invert()
  val matVC = Matrix4f().perspective(FMath.Pi / 4, 4f / 3f, 0.1f, 100f)
  val matCV = Matrix4f(matVC).invert()
  val matMW = Matrix4f() // .rotate(0.5f, Vector3f(2f, 3f, 5f).normalize())
  val matMC = Matrix4f(matVC).mul(matWV).mul(matMW)

  var cuboid: Cuboid = null
  var spheroid: Spheroid = null
  var program: Program = null

  override def init() =
    Mouse.movedListeners += mouseMoved
    cuboid = Cuboid(1f, 1f, 1f)
    spheroid = Spheroid(2f, 16, 8)
    program = Program(srcFile("Picking.vert"), srcFile("Picking.frag"))
    program.bind()
    glClearColor(0, 0, 0, 1)
    glEnable(GL_CULL_FACE)
    glCullFace(GL_BACK)
    glPolygonMode(GL_FRONT, GL_LINE)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    program.setUniform("matMC", matMC)
    // cuboid.draw()
    spheroid.draw()

  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    val ray = Ray3(Vector3f(0f, 2f, 20f), worldRay(x, y))
    val sph = Sphere3(Vector3f(0f, 0f, 0f), 2f)
    println(ray.testIntersectionWith(sph))

  private def worldRay(mouseX: Float, mouseY: Float) =
    val sx = Window.sizeX.toFloat
    val sy = Window.sizeY.toFloat
    val deviceCoords = toDeviceCoords(mouseX, mouseY, sx, sy)
    // println(deviceCoords.x+", "+deviceCoords.y);
    val clipCoords = Vector4f(deviceCoords.x, deviceCoords.y, -1f, 1f)
    val eyeCoords = toEyeCoords(clipCoords)
    val worldRay = toWorldCoords(eyeCoords)
    worldRay

  private def toWorldCoords(eyeCoords: Vector4f) =
    val rayWorld = matVW.transform(eyeCoords)
    val mouseRay = Vector3f(rayWorld.x, rayWorld.y, rayWorld.z)
    mouseRay.normalize()
    mouseRay

  private def toEyeCoords(clipCoords: Vector4f) =
    val eyeCoords = matCV.transform(clipCoords)
    Vector4f(eyeCoords.x, eyeCoords.y, -1f, 0f)

  // normalized device coords, mouse pos, vieport size
  private def toDeviceCoords(mx: Float, my: Float, sx: Float, sy: Float) =
    val x = (2 * mx) / sx - 1
    val y = (2 * my) / sy - 1f
    Vector2f(x, -y)
