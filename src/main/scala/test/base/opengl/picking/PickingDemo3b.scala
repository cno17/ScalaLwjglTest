package test.base.opengl.picking

import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.input.Mouse
import yage.base.glfw.window.Window
import yage.scene.camera.SceneView
import yage.scene.primitive.Spheroid
import yage.shape.Ray3
import yage.shape.Sphere3

import java.io.File

object PickingDemo3b extends GlApp:

  var view: SceneView = null
  var spheroid: Spheroid = null
  var program: Program = null

  var matMW = Matrix4f()
  var matMC = Matrix4f()

  override def init() =
    view = SceneView()
    view.viewport.set(0f, 0f, Window.sizeX.toFloat, Window.sizeY.toFloat)
    view.camera.moveTo(0f, 0f, 20f)
    // view.camera.lookAt(0f, 0f, 0f, 0f, 1f, 0f)
    spheroid = Spheroid(1f, 32, 16)
    program = Program(srcFile("Picking.vert"), srcFile("Picking.frag"))
    program.setUniform("color", 0.2f, 0.8f, 0.2f)
    program.bind()
    matMC.set(view.camera.matVC).mul(view.camera.matWV).mul(matMW)
    Mouse.movedListeners += mouseMoved

    glClearColor(0, 0, 0, 1)
    glEnable(GL_CULL_FACE)
    glCullFace(GL_BACK)
    glPolygonMode(GL_FRONT, GL_LINE)


  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    program.setUniform("matMC", matMC)
    spheroid.draw()

  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    val ray = getWorldRay2(x, y)
    val sph = Sphere3(Vector3f(0f, 0f, 0f), 1f)
    if ray.testIntersectionWith(sph) then program.setUniform("color", 0.8f, 0.8f, 0.8f)
    else program.setUniform("color", 0.2f, 0.8f, 0.2f)

  private def getWorldRay2(mouseX: Float, mouseY: Float) =
    val camera = view.camera
    val dirC = Vector3f()
    val dirV = Vector3f()
    val dirW = Vector3f()
    val x = (2 * mouseX) / view.viewport.w - 1
    val y = (2 * mouseY) / view.viewport.h - 1
    dirC.set(x, -y, -1f)
    camera.matCV.transformDirection(dirC, dirV)
    dirV.z = -1f
    camera.matVW.transformDirection(dirV, dirW)
    val org = camera.matVW.getTranslation(Vector3f())
    Ray3(org, dirW.normalize())
