package demo.base.learn.c1

import demo.base.learn.util.Camera
import demo.base.learn.util.CameraController
import demo.base.learn.util.ProgramExt
import org.joml.Matrix4f
import org.lwjgl.opengl.GL11C.*
import yage.base.FMath
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.scene.primitive.Cuboid

import java.io.File

object CameraDemo extends GlApp, ProgramExt:

  var program: Program = null
  var texture: Texture2 = null
  var cuboid: Cuboid = null
  var camera: Camera = null
  var controller: CameraController = null

  val matMW = Matrix4f().identity()
  val matMC = Matrix4f().identity()

  override def init() =
    program = Program(srcFile("Camera.vert"), srcFile("Camera.frag"))
    texture = Texture2(resFile("Images/Wood1.jpg"))
    texture.bindToTextureUnit(0)
    cuboid = Cuboid(3f, 0.2f, 3f)
    camera = Camera()
    controller = CameraController(camera)
    // TODO controller.addListenersTo(keyboard, mouse)
    glClearColor(0, 0, 0, 1)
    glEnable(GL_CULL_FACE)
    glCullFace(GL_BACK)
    glPolygonMode(GL_FRONT, GL_FILL)
    camera.matVW.identity().translate(0.0f, 2.0f, 20.0f)
    camera.matVC.identity().perspective(FMath.Pi / 4, 4f / 3f, 0.1f, 100f)

  // override def resized(sx: Int, sy: Int) = println(sx)

  override def draw() =
    val sx = Window.sizeX.toFloat
    val sy = Window.sizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    program.bind()
    // matMW.identity().rotate(glfwGetTime().toFloat * 1.6f, Vector3f(0.0f, 2.0f, 0.0f).normalize())
    // camera.matVW.identity().translate(0.0f, 0.0f, 8.0f)
    // camera.matVC.identity().perspective(Math.toRadians(45.0).toFloat, sx.toFloat / sy, 0.1f, 100f)
    camera.update()
    matMC.set(camera.matWC).mul(matMW)
    program.setUniform("matMC", matMC)
    cuboid.draw()
