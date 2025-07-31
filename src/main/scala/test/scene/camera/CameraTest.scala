package test.scene.camera

import org.joml.Matrix4f
import yage.base.opengl.context.Capability.CullFace
import yage.base.opengl.context.Face.FrontAndBack
import yage.base.opengl.context.PolygonMode.Line
import yage.base.opengl.primitive.Primitive
import yage.base.glfw.GlApp
import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.window.Window
import yage.scene.camera.Camera
import yage.scene.material.ColorMaterial
import yage.scene.primitive.Model

object CameraTest extends GlApp:

  var matMW = Matrix4f()
  var matMC = Matrix4f()
  var camera: Camera = null
  var primitive: Primitive = null
  var material: ColorMaterial = null

  override def init() =
    camera = Camera()
    camera.moveTo(0, 0, 10)
    // primitive = Cuboid(1f, 1f, 1f)
    primitive = Model(resFile("Meshes/Teapot2.obj"))
    material = ColorMaterial(0.8f, 0.8f, 0.8f)
    material.program.setMaterial(material) // TODO seems awkward!
    material.program.bind()
    glContext.enable(CullFace)
    glContext.setClearColor(0, 0, 0)
    glContext.setPolygonMode(FrontAndBack, Line)
    Keyboard.keyPressedListeners += keyPressed
    Keyboard.keyRepeatedListeners += keyPressed
    Window.setTitle("Press Control-Key to rotate!")

  override def draw() =
    matMC.set(camera.matVC).mul(camera.matWV).mul(matMW)
    material.program.setUniform("matMC", matMC)
    glContext.clear()
    primitive.draw()

  def keyPressed(k: Key) =
    if k == Key.A then camera.alignYUp()
    if k == Key.L then camera.lookAt(0, 0, 0)
    if Keyboard.isControlKeyPressed then rotate(k) else translate(k)

  def rotate(k: Key) =
    if k == Key.Up then camera.turnX(-0.01f)
    if k == Key.Down then camera.turnX(0.01f)
    if k == Key.Left then camera.turnY(0.01f)
    if k == Key.Right then camera.turnY(-0.01f)

  def translate(k: Key) =
    if k == Key.Left then camera.move(-0.1f, 0, 0)
    if k == Key.Right then camera.move(0.1f, 0, 0)
    if k == Key.Up then camera.move(0, 0, -0.1f)
    if k == Key.Down then camera.move(0, 0, 0.1f)
    if k == Key.L then camera.lookAt(0, 0, 0)


