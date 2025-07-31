package demo.base.learn.c2

import yage.scene.primitive.Cuboid
import demo.base.learn.util.ProgramExt
import demo.base.learn.util.light.SpotLight
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.FMath.Pi

import java.io.File

object SpotDemo extends GlApp, ProgramExt:

  var light: SpotLight = null
  var diffuseMap: Texture2 = null
  var specularMap: Texture2 = null
  var program: Program = null
  var cuboid: Cuboid = null

  val matVW = Matrix4f().translate(0.0f, 0.0f, 20.0f)
  val matWV = Matrix4f(matVW).invert()
  val matVC = Matrix4f().perspective(Pi / 4, 4f / 3f, 0.1f, 100f)
  val matMW = Matrix4f()
  val matMV = Matrix4f(matWV).mul(matMW)

  override def init() =
    light = SpotLight(Vector4f(0f, 0f, 0f, 1f), Vector4f(0f, 0, -1f, 0f))
    light.intensity.a.set(0.4f)
    diffuseMap = Texture2(resFile("Images/Container.png"))
    specularMap = Texture2(resFile("Images/Container_Specular.png"))
    diffuseMap.bindToTextureUnit(0)
    specularMap.bindToTextureUnit(1)
    program = Program(srcFile("Spot.vert"), srcFile("Spot.frag"))
    program.bind()
    // for v <- program.uniformVariableNames do println(v.name)
    // System.exit(0)

    program.setUniform("light.pos", light.pos)
    program.setUniform("light.dir", light.dir)
    program.setUniform("light.maxAng", 0.1f)
    program.setUniform("light.angAtt", 50f)
    program.setUniform("light.ambient", light.intensity.a)
    program.setUniform("light.diffuse", light.intensity.d)
    program.setUniform("light.specular", light.intensity.s)
    program.setUniform("material.diffuse", 0)
    program.setUniform("material.specular", 1)
    program.setUniform("material.shininess", 64f)

    cuboid = Cuboid(5f, 5f, 1f)
    glEnable(GL_DEPTH_TEST)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", matVC)
    cuboid.draw()

  override def step(t: Int, dt: Int) =
    val ax = timer.time.toFloat * 0.0001f
    val ay = timer.time.toFloat * 0.0002f
    val az = timer.time.toFloat * 0.0003f
    // dInt.x = 0.5f + 0.5f * sin(ax)
    // dInt.y = 0.5f + 0.5f * sin(ay)
    // dInt.z = 0.5f + 0.5f * sin(az)
    // matMW.rotate(0.002f, Vector3f(ax, ay, az).normalize())
    matMW.rotate(0.002f, Vector3f(0, 1, 0).normalize())
    matMV.set(matWV).mul(matMW)
