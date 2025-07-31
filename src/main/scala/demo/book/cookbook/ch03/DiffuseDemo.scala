package demo.book.cookbook.ch03

import org.joml.Matrix4f
import yage.base.opengl.context.Capability.CullFace
import yage.base.opengl.context.Capability.DepthTest
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.shader.program.simple.DiffuseProgram
import yage.base.glfw.GlApp
import yage.base.par.TriMesh
import yage.base.FMath.sin
import yage.scene.camera.Camera
import yage.scene.light.PointLight
import yage.scene.material.LightMaterial
import yage.scene.primitive.Cuboid
import yage.scene.primitive.Hull
import yage.scene.primitive.Model
import yage.scene.primitive.ParMesh
import yage.scene.primitive.Platonic
import yage.scene.primitive.Platonic.Type.Dodecaahedron
import yage.scene.primitive.Torus

// todo: Node

object DiffuseDemo extends GlApp:

  var primitive: Primitive = null
  var material: LightMaterial = null
  var light: PointLight = null
  var camera: Camera = null
  var program: DiffuseProgram = null

  val matMW = Matrix4f()
  val matMV = Matrix4f()

  override def init() =
    primitive = Torus(0.4f, 2.0f, 32, 16)
    primitive = Cuboid(0.8f, 2.0f, 2.0f)
    primitive = Platonic(Dodecaahedron)
    primitive = Hull.inSpheroid(20, 2f, 2f, 2f)
    primitive = ParMesh(TriMesh.trefoilKnot(20, 20, 1f).scale(1f))
    primitive = Model(resFile("Meshes/Teapot2.obj"))
    material = LightMaterial()
    material.diffuseColor.set(0.2f, 0.8f, 0.5f)
    light = PointLight()
    camera = Camera()
    camera.moveTo(0, 0, 10)
    program = DiffuseProgram()
    program.bind()
    glContext.enable(CullFace)
    glContext.enable(DepthTest)
    // glContext.setPolygonMode(Face.FrontAndBack, PolygonMode.Line)
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
    program.setUniform("light.ambient", light.ambientColor)
    program.setUniform("light.diffuse", light.diffuseColor)
    program.setUniform("light.posV", light.posV)


