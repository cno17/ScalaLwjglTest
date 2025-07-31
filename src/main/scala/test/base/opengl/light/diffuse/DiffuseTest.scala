package test.base.opengl.light.diffuse

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL11C.glClearColor
import org.lwjgl.opengl.GL11C.glEnable
import org.lwjgl.opengl.GL11C.glFrontFace
import org.lwjgl.opengl.GL11C.glPolygonMode
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.primitive.Torus
import org.joml.Matrix4f
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.WindowCreateInfo
import yage.scene.primitive.Rectangle

import java.io.File

object DiffuseTest extends GlApp:

  var program: Program = null
  var primitive: Primitive = null

//  var matMW = Matrix4().toOne()
//  var matVW = Matrix4().toTranslation(0, 0, 10)
//  var matWV = Matrix4().set(matVW).invert() // TODO matWV.inverted
//  var matVC = Matrix4().toFrustum(-1, 1, -1, 1, 2, 1000)
//  var matMV = Matrix4().set(matWV).multiply(matMW)
  var matMW = Matrix4f()
  var matVW = Matrix4f().translation(0, 0, 10)
  var matWV = Matrix4f().set(matVW).invert() // TODO matWV.inverted
  var matVC = Matrix4f().frustum(-1, 1, -1, 1, 2, 1000)
  var matMV = Matrix4f().set(matWV).mul(matMW)
  var buf = BufferUtils.createFloatBuffer(16)

  override def init() =
    program = Program(srcFile("DiffuseOld.vert"), srcFile("DiffuseOld.frag"))
    primitive = Torus(1f, 2f, 64, 32)
    // primitive = Rectangle(-1f, -1, 1f, 1f)
    glClearColor(0, 0, 0, 1)
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL)
    glFrontFace(GL_CCW)
    // glEnable(GL_CULL_FACE)
    glEnable(GL_DEPTH_TEST)

  override def draw() =
    // updateUniforms()
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    program.bind()
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", matVC)
//    program.setUniform("dInt", 0.0f, 0.8f, 0.0f)
//    program.setUniform("dRef", 0.0f, 0.8f, 0.0f)
    primitive.draw()

  def updateUniforms() =
    program.setUniform("lightPosV", 0.0f, 0.0f, 0.0f, 1.0f)
    program.setUniform("dInt", 0.8f, 0.8f, 0.8f)
    program.setUniform("dRef", 0.8f, 0.8f, 0.8f)

  override def step(t: Int, dt: Int) =
    //matVM =* (matVW, matWM)
    //matCM =* (matCV, matVM)
    //matCM >> (buf, 0)
    println()
