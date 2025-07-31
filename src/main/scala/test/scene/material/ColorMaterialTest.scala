package test.scene.material

import org.joml.Matrix4f
import yage.base.opengl.context.Capability.CullFace
import yage.base.opengl.context.Face.FrontAndBack
import yage.base.opengl.context.PolygonMode.Line
import yage.base.opengl.primitive.Primitive
import yage.base.glfw.GlApp
import yage.base.FMath.Pi
import yage.scene.material.ColorMaterial
import yage.scene.primitive.Torus

// todo: use camera and context

object ColorMaterialTest extends GlApp:

  var primitive: Primitive = null
  var material: ColorMaterial = null

  val matVW = Matrix4f().translate(0.0f, 0.0f, 10.0f)
  val matWV = Matrix4f(matVW).invert()
  val matVC = Matrix4f().perspective(Pi / 4, 4f / 3f, 0.1f, 100f)
  val matMW = Matrix4f()
  val matMC = Matrix4f(matVC).mul(matWV).mul(matMW)

  override def init() =
    primitive = Torus(0.4f, 2.0f, 32, 16)
    material = ColorMaterial()
    material.color.set(0.2f, 0.8f, 0.2f)
    material.program.setMaterial(material) // TODO seems awkward!
    material.program.bind()
    glContext.enable(CullFace)
    glContext.setClearColor(0, 0, 0)
    glContext.setPolygonMode(FrontAndBack, Line)

  override def draw() =
    glContext.clear()
    material.program.setUniform("matMC", matMC)
    primitive.draw()

  override def step(t: Int, dt: Int) =
    matMW.rotateX(0.002f)
    // matMW.rotateY(0.002f)
    // matMW.rotateZ(0.003f)
    matMC.set(matVC).mul(matWV).mul(matMW)
