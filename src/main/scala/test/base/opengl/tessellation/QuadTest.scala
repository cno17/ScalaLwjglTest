package test.base.opengl.tessellation

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL20C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL40C.*
import yage.base.glfw.input.Keyboard
import yage.base.glfw.window.Window
// import test.graphics.fw.TrafoControllerTest.mat
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.input.Key
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.WindowCreateInfo

import java.io.File

// TODO use yage.Buffer

object Quad extends GlApp:

  var program: Program = null
  var vao = 0
  var name = "inner0"

  override def info() =
    val info = WindowCreateInfo()
    info.eventMode = EventMode.Wait
    info

  override def init() =
    Keyboard.keyPressedListeners += keyPressed
    debugMessenger.enable()
    // TODO simplify
    program = Program(File(srcDir, "Quad.vert"), File(srcDir, "Quad.tesc"), File(srcDir, "Quad.tese"), File(srcDir, "Quad.frag"))
    program.setUniform("inner", 4)
    program.setUniform("outer", 4)
    program.bind()
    glClearColor(0, 0, 0, 1)
    glPolygonMode(GL_FRONT_AND_BACK, GL_LINE)
    // glFrontFace(GL_CCW)
    // glEnable(GL_CULL_FACE)

    val pos = Array(-0.8f, -0.8f, 0.8f, -0.8f, 0.8f, 0.8f, -0.8f, 0.8f)
    val vbo = glGenBuffers()
    glBindBuffer(GL_ARRAY_BUFFER, vbo)
    glBufferData(GL_ARRAY_BUFFER, pos, GL_STATIC_DRAW)
    vao = glGenVertexArrays()
    glBindVertexArray(vao)
    glBindBuffer(GL_ARRAY_BUFFER, vbo)
    glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0)
    // Set up patch VAO
    glEnableVertexAttribArray(0)
    glBindVertexArray(0)
    // Set the number of vertices per patch.  IMPORTANT!!
    glPatchParameteri(GL_PATCH_VERTICES, 4)

  override def draw() =
    Window.setTitle(name)
    glClear(GL_COLOR_BUFFER_BIT)
    glBindVertexArray(vao)
    glDrawArrays(GL_PATCHES, 0, 4)

  def reset() =
    program.setUniform("inner0", 4)
    program.setUniform("inner1", 4)
    program.setUniform("outer0", 4)
    program.setUniform("outer1", 4)
    program.setUniform("outer2", 4)
    program.setUniform("outer3", 4)


  def keyPressed(k: Key) =
    if k == Key.R then reset()
    if k == Key.I then name = "inner"
    if k == Key.O then name = "outer"
    if k == Key.K_0 then name = name.substring(0, 5) + "0"
    if k == Key.K_1 then name = name.substring(0, 5) + "1"
    if k == Key.K_2 then name = name.substring(0, 5) + "2"
    if k == Key.K_3 then name = name.substring(0, 5) + "3"
    if k == Key.Left then
      val v = program.getUniformi(name)
      if v > 1 then program.setUniform(name, v - 1)
    if k == Key.Right then
      val v = program.getUniformi(name)
      if v < 100 then program.setUniform(name, v + 1)
    draw()
