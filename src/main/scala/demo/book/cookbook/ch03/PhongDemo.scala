package demo.book.cookbook.ch03

import org.joml.Matrix4f
import yage.base.opengl.context.Capability.CullFace
import yage.base.opengl.context.Capability.DepthTest
import yage.base.opengl.context.Face.Back
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.shader.program.simple.PhongProgram
import yage.base.glfw.GlApp
import yage.base.FMath.sin
import yage.scene.camera.Camera
import yage.scene.light.PointLight
import yage.scene.material.LightMaterial
import yage.scene.primitive.Cuboid
import yage.scene.primitive.Hull
import yage.scene.primitive.Model
import yage.scene.primitive.Torus

// todo: node

object PhongDemo extends GlApp:

  var primitive: Primitive = null
  var material: LightMaterial = null
  var light: PointLight = null
  var camera: Camera = null
  var program: PhongProgram = null

  val matMW = Matrix4f()
  val matMV = Matrix4f()

  override def init() =
    primitive = Cuboid(0.8f, 2.0f, 2.0f)
    primitive = Torus(0.5f, 2.0f, 64, 32)
    primitive = Model(resFile("Meshes/Teapot2.obj"))
    primitive = Hull.inSpheroid(300, -1f, 2f, 3f)
    material = LightMaterial()
    light = PointLight()
    camera = Camera()
    camera.moveTo(0, 0, 10)
    program = PhongProgram()
    program.bind()

    glContext.enable(CullFace)
    glContext.enable(DepthTest)
    glContext.cullFace(Back)
    // glContext.setPolygonMode(FrontAndBack, Line)
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

