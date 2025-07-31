package demo.base.learn.c2

// import demo.lwjgl.learn.util.Cuboid
import yage.scene.primitive.Cuboid
import demo.base.learn.util.ProgramExt
import demo.base.learn.util.light.PointLight
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.primitive.Cylinder
import yage.scene.primitive.Octasphere
import yage.scene.primitive.Spheroid
import yage.scene.primitive.Torus
import yage.base.FMath.Pi

import java.io.File

object MaterialMapDemo extends GlApp, ProgramExt:

  var light: PointLight = null
  var diffuseMap: Texture2 = null
  var specularMap: Texture2 = null
  var program: Program = null
  var primitive: Primitive = null

  val matVW = Matrix4f().identity().translate(0.0f, 0.0f, 10.0f)
  val matWV = Matrix4f().set(matVW).invert()
  val matVC = Matrix4f().identity().perspective(Pi / 4, 4f / 3f, 0.1f, 100f)
  val matMW = Matrix4f().identity()
  val matMV = Matrix4f().set(matWV).mul(matMW)

  override def init() =
    light = PointLight(Vector4f(0f, 5f, 10f, 1f))
    diffuseMap = Texture2(resFile("Images/Metal/Metal3-Diffuse.png"))
    specularMap = Texture2(resFile("Images/Metal/Metal3-Specular.png"))
    diffuseMap.bindToTextureUnit(0)
    specularMap.bindToTextureUnit(1)
    program = Program(srcFile("MaterialMap.vert"), srcFile("MaterialMap.frag"))
    program.bind()
    program.setUniform("light.pos", light.pos)
    program.setUniform("light.ambient", light.intensity.a)
    program.setUniform("light.diffuse", light.intensity.d)
    program.setUniform("light.specular", light.intensity.s)
    program.setUniform("material.ambient", Vector3f(1f, 1f, 1f))
    program.setUniform("material.diffuse", 0)
    program.setUniform("material.specular", 1)
    program.setUniform("material.shininess", 64f)

    primitive = Spheroid(0.8f, 2.0f, 1.2f, 32, 16)
    primitive = Torus(0.4f, 2.0f, 32, 16)
    primitive = Cylinder(1.0f, 0.4f, 5.0f, 16, 8)
    primitive = Octasphere(2, 3, 5, 0.2f, 2)
    primitive = Cuboid(0.8f, 2.0f, 2.0f)
    glEnable(GL_DEPTH_TEST)
    // glPolygonMode(GL_FRONT_AND_BACK, GL_LINE)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", matVC)
    primitive.draw()

  override def step(t: Int, dt: Int) =
    val ax = timer.time.toFloat * 0.0001f
    val ay = timer.time.toFloat * 0.0002f
    val az = timer.time.toFloat * 0.0003f
    // dInt.x = 0.5f + 0.5f * sin(ax)
    // dInt.y = 0.5f + 0.5f * sin(ay)
    // dInt.z = 0.5f + 0.5f * sin(az)
    matMW.rotate(0.005f, Vector3f(ax, ay, az).normalize())
    matMV.set(matWV).mul(matMW)
