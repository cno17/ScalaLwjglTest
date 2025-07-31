package demo.base.learn.c2

import yage.scene.primitive.Cuboid
import demo.base.learn.util.ProgramExt
import demo.base.learn.util.light.Material
import demo.base.learn.util.light.PointLight
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.FMath.Pi

import java.io.File

// todo:
//   program.getOffsets(?)
//   program.setUniform(light)

object PhongDemo2 extends GlApp, ProgramExt:

  var light: PointLight = null
  var material: Material = null
  var cuboid: Cuboid = null
  var program: Program = null

  val matVW = Matrix4f().identity().translate(0.0f, 0.0f, 10.0f)
  val matWV = Matrix4f().set(matVW).invert()
  val matVC = Matrix4f().identity().perspective(Pi / 4, 4f / 3f, 0.1f, 100f)
  val matMW = Matrix4f().identity()
  val matMV = Matrix4f().set(matWV).mul(matMW)

  override def init() =
    light = PointLight(Vector4f(0f, 1f, 0f, 1f))
    material = Material()
    cuboid = Cuboid(0.2f, 2.0f, 2.0f)
    program = Program(srcFile("Phong2.vert"), srcFile("Phong2.frag"))
    program.bind()
    glEnable(GL_DEPTH_TEST)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", matVC)
    program.setUniform("light.posV", 0.0f, 3.0f, 0.0f, 1f)
    program.setUniform("light.intensity.a", light.intensity.a)
    program.setUniform("light.intensity.d", light.intensity.d)
    program.setUniform("light.intensity.s", light.intensity.s)
    program.setUniform("material.reflectivity.a", material.reflectivity.a)
    program.setUniform("material.reflectivity.d", material.reflectivity.d)
    program.setUniform("material.reflectivity.s", material.reflectivity.s)
    program.setUniform("material.shininess", material.shininess)
    cuboid.draw()

  override def step(t: Int, dt: Int) =
    val ax = timer.time.toFloat * 0.0001f
    val ay = timer.time.toFloat * 0.0002f
    val az = timer.time.toFloat * 0.0003f
    // dInt.x = 0.5f + 0.5f * sin(ax)
    // dInt.y = 0.5f + 0.5f * sin(ay)
    // dInt.z = 0.5f + 0.5f * sin(az)
    matMW.rotate(0.01f, Vector3f(ax, ay, az).normalize())
    matMV.set(matWV).mul(matMW)
