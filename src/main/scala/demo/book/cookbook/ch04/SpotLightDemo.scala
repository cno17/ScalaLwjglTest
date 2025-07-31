package demo.book.cookbook.ch04

import org.joml.Matrix4f
import yage.base.opengl.context.Capability.DepthTest
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.FMath.sin
import yage.scene.camera.Camera
import yage.scene.light.SpotLight
import yage.scene.material.LightMaterial
import yage.scene.primitive.Cuboid

// todo: find spot light bug!

object SpotLightDemo extends GlApp:

  var cuboid: Cuboid = null
  var material: LightMaterial = null
  var light: SpotLight = null
  var camera: Camera = null

  // var program: LightProgram = null
  var program: Program = null

  val matMW = Matrix4f()
  val matMV = Matrix4f()

  override def init() =
    cuboid = Cuboid(2f, 2f, 0.1f)
    material = LightMaterial()
    light = SpotLight()
    light.posV.set(0, 0, 10, 1)
    light.maxAng = 0.2f
    light.angAtt = 1000f
    camera = Camera()
    camera.moveTo(0, 0, 10)
    program = Program(srcFile("SpotLight.vert"), srcFile("SpotLight.frag"))
    program.bind()
    glContext.enable(DepthTest)
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    setUniforms()
    glContext.clear()
    cuboid.draw()

  override def step(t: Int, dt: Int) =
    material.diffuseColor.x = 0.5f + 0.5f * sin(0.0002f * t)
    material.diffuseColor.y = 0.5f + 0.5f * sin(0.0003f * t)
    material.diffuseColor.z = 0.5f + 0.5f * sin(0.0005f * t)
    matMW.rotateX(0.0002f * dt)
    matMW.rotateY(0.0003f * dt)
    matMW.rotateZ(0.0005f * dt)
    matMV.set(camera.matWV).mul(matMW)

  private def setUniforms() =
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", camera.matVC)
    program.setUniform("material.diffuse", material.diffuseColor)
    program.setUniform("material.specular", material.specularColor)
    program.setUniform("material.shininess", material.shininess)
    program.setUniform("light.ambient", light.ambientColor)
    program.setUniform("light.diffuse", light.diffuseColor)
    program.setUniform("light.specular", light.specularColor)
    program.setUniform("light.posV", light.posV)
    program.setUniform("light.dirV", light.dirV)
    program.setUniform("light.maxAng", light.maxAng)
    program.setUniform("light.angAtt", light.angAtt)
