package demo.book.cookbook.ch06

import org.joml.Matrix4f
import yage.base.opengl.context.Capability.CullFace
import yage.base.opengl.context.Capability.DepthTest
import yage.base.opengl.context.Face
import yage.base.opengl.context.Face.Back
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.FMath.Pi
import yage.base.FMath.sin
import yage.scene.light.PointLight
import yage.scene.material.LightMaterial
import yage.scene.primitive.Cuboid
import yage.scene.primitive.Torus

// todo: camera, node, hull bug

object EdgeDetectionDemo extends GlApp:

  var primitive: Primitive = null
  var material: LightMaterial = null
  var light: PointLight = null
  // var camera: Camera = null
  var program: Program = null

  val matVW = Matrix4f().identity().translate(0.0f, 0.0f, 10.0f)
  val matWV = Matrix4f().set(matVW).invert()
  val matVC = Matrix4f().identity().perspective(Pi / 4, 4f / 3f, 0.1f, 100f)
  val matMW = Matrix4f().identity()
  val matMV = Matrix4f().set(matWV).mul(matMW)

  override def init() =
    primitive = Torus(0.4f, 2.0f, 32, 16)
    // primitive = TrefoilKnot(10, 10, 2f)
    primitive = Cuboid(0.8f, 2.0f, 2.0f)
    material = LightMaterial()
    light = PointLight()
    program = Program(srcFile("Diffuse.vert"), srcFile("Diffuse.frag"))
    program.bind()

    glContext.enable(CullFace)
    glContext.enable(DepthTest)
    glContext.cullFace(Back)
    // glContext.setPolygonMode(Face.Front, PolygonMode.Line)
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    setUniforms()
    glContext.clear()
    primitive.draw()

  override def step(t: Int, dt: Int) =
    material.diffuseColor.x = 0.5f + 0.5f * sin(0.0002f * t)
    material.diffuseColor.y = 0.5f + 0.5f * sin(0.0003f * t)
    material.diffuseColor.z = 0.5f + 0.5f * sin(0.0005f * t)
    matMW.rotateX(0.0002f * dt)
    matMW.rotateY(0.0003f * dt)
    matMW.rotateZ(0.0005f * dt)
    matMV.set(matWV).mul(matMW)


  private def setUniforms() =
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", matVC)
    program.setUniform("material.diffuse", material.diffuseColor)
    program.setUniform("light.ambient", light.ambientColor)
    program.setUniform("light.diffuse", light.diffuseColor)
    program.setUniform("light.posV", light.posV)


