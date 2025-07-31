package demo.base.learn.c2

import yage.scene.primitive.Cuboid
import demo.base.learn.util.ProgramExt
import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp

import yage.base.FMath.Pi

import java.io.File

object PhongDemo extends GlApp, ProgramExt:

  var program: Program = null
  var cuboid: Cuboid = null

  val aInt = Vector3f(0.2f, 0.2f, 0.2f)
  val dInt = Vector3f(1f, 1f, 1f)
  val sInt = Vector3f(1f, 1f, 1f)

  val aRef = Vector3f(1f, 1f, 1f)
  val dRef = Vector3f(1f, 1f, 1f)
  val sRef = Vector3f(1f, 1f, 1f)
  val shininess = 64f

  val matVW = Matrix4f().identity().translate(0.0f, 0.0f, 10.0f)
  val matWV = Matrix4f().set(matVW).invert()
  val matVC = Matrix4f().identity().perspective(Pi / 4, 4f / 3f, 0.1f, 100f)
  val matMW = Matrix4f().identity()
  val matMV = Matrix4f().set(matWV).mul(matMW)

  override def init() =
    program = Program(srcFile("Phong.vert"), srcFile("Phong.frag"))
    program.bind()
    program.setUniform("lightPosV", 0.0f, 3.0f, 0.0f, 1f)
    cuboid = Cuboid(0.2f, 2.0f, 2.0f)
    glEnable(GL_DEPTH_TEST)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", matVC)
    program.setUniform("aInt", aInt)
    program.setUniform("dInt", dInt)
    program.setUniform("sInt", sInt)
    program.setUniform("aRef", aRef)
    program.setUniform("dRef", dRef)
    program.setUniform("sRef", sRef)
    program.setUniform("shininess", shininess)
    cuboid.draw()

  override def step(t: Int, dt: Int) =
    val ax = timer.time.toFloat * 0.0001f
    val ay = timer.time.toFloat * 0.0002f
    val az = timer.time.toFloat * 0.0003f
    // dInt.x = 0.5f + 0.5f * sin(ax)
    // dInt.y = 0.5f + 0.5f * sin(ay)
    // dInt.z = 0.5f + 0.5f * sin(az)
    matMW.rotate(0.002f, Vector3f(ax, ay, az).normalize())
    matMV.set(matWV).mul(matMW)
