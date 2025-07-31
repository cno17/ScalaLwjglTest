package demo.book.cookbook.ch08

import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.*
import yage.base.opengl.context.Capability.CullFace
import yage.base.opengl.context.Capability.DepthTest
import yage.base.opengl.framebuffer.FrameBuffer
import yage.base.opengl.framebuffer.FrameBuffer.Attachment.Depth
import yage.base.opengl.resource.Format
import yage.base.opengl.resource.texture.TexelFormat
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.resource.texture.Texture.MagFilter
import yage.base.opengl.resource.texture.Texture.MinFilter.Nearest
import yage.base.opengl.resource.texture.Texture.WrapMode.ClampToEdge
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.window.WindowCreateInfo
import yage.base.FMath.Pi

import java.io.File

/** Simple demo to showcase shadow mapping with a custom shader doing perspective divide
 * and depth test (i.e. no sampler2DShadow).
 *
 * @author
 * Kai Burjack
 */

object ShadowMapDemo extends GlApp:

  val width = 1200
  val height = 800
  val shadowMapSize = 1024

  val boxes = Scene.boxes2
  val up = Vector3f(0.0f, 1.0f, 0.0f)

  var cameraPosition = Vector3f(-3.0f, 6.0f, 6.0f)
  var cameraLookAt = Vector3f(0.0f, 0.0f, 0.0f)

  var lightPosition = Vector3f(6.0f, 3.0f, 6.0f)
  var lightLookAt = Vector3f(0.0f, 1.0f, 0.0f)

  var lightDistance = 10.0f
  var lightHeight = 4.0f
  
  var vao: Int = 0
  var vbo: Int = 0

  var prog1: Program = null
  var prog2: Program = null

  var depthTexture: Texture2 = null
  var frameBuffer: FrameBuffer = null


  var light = Matrix4f()
  var camera = Matrix4f()
  var biasMatrix = Matrix4f(
    0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f, 0.5f, 0.5f, 0.5f, 1.0f
  )

  override def info() =
    val info = WindowCreateInfo()
    info.sizeX = width
    info.sizeY = height
    info

  override def init() =
    // Set some GL states
    glContext.enable(CullFace)
    glContext.enable(DepthTest)
    glContext.setClearColor(0.2f, 0.3f, 0.4f, 1.0f)
    createVao()
    prog1 = Program(srcFile("ShadowMap1.vert"), srcFile("ShadowMap1.frag"))
    prog2 = Program(srcFile("ShadowMap2.vert"), srcFile("ShadowMap2.frag"))
    prog2.setUniform("depthTexture", 0)
    depthTexture = Texture2(TexelFormat.DepthComponent, shadowMapSize, shadowMapSize)
    depthTexture.setMinFilter(Nearest)
    depthTexture.setMagFilter(MagFilter.Nearest)
    depthTexture.setWrapModeU(ClampToEdge)
    depthTexture.setWrapModeV(ClampToEdge)
    frameBuffer = FrameBuffer()
    frameBuffer.attach(depthTexture, 0, Depth)

  // Creates a VAO for the scene with some boxes.
  def createVao() =
    vao = GL30C.glGenVertexArrays()
    val vbo = GL15C.glGenBuffers()
    GL30C.glBindVertexArray(vao)
    GL15C.glBindBuffer(GL15C.GL_ARRAY_BUFFER, vbo)
    val bb = BufferUtils.createByteBuffer(boxes.size * 4 * (3 + 3) * 6 * 6)
    val fv = bb.asFloatBuffer()
    var i = 0
    while (i < boxes.size)
      DemoUtils.triangulateBox(boxes(i), boxes(i + 1), fv)
      i += 2

    GL15C.glBufferData(GL15C.GL_ARRAY_BUFFER, bb, GL15C.GL_STATIC_DRAW)
    GL20C.glEnableVertexAttribArray(0)
    GL20C.glVertexAttribPointer(0, 3, GL11C.GL_FLOAT, false, 4 * (3 + 3), 0L)
    GL20C.glEnableVertexAttribArray(1)
    GL20C.glVertexAttribPointer(1, 3, GL11C.GL_FLOAT, false, 4 * (3 + 3), (4 * 3))
    GL15C.glBindBuffer(GL15C.GL_ARRAY_BUFFER, 0)
    GL30C.glBindVertexArray(0)


  // Update the camera MVP matrix.
  override def step(t: Int, dt: Int) =
    // Update light
    val alpha = System.currentTimeMillis() / 1000.0 * 0.5
    val x = Math.sin(alpha).toFloat
    val z = Math.cos(alpha).toFloat
    lightPosition.set(lightDistance * x, 3 + Math.sin(alpha).toFloat, lightDistance * z)
    light
      .setPerspective(Pi / 4, 1.0f, 0.1f, 60.0f)
      .lookAt(lightPosition, lightLookAt, up)
    // Update camera
    camera
      .setPerspective(Pi / 4, width.toFloat / height, 0.1f, 30.0f)
      .lookAt(cameraPosition, cameraLookAt, up)

  override def draw() =
    renderShadowMap()
    renderNormal()

  // Render the shadow map into a depth texture.
  def renderShadowMap() =
    prog1.bind()
    // Set MVP matrix of the "light camera"
    prog1.setUniform("viewProjectionMatrix", light)
    frameBuffer.bind()
    glContext.setViewport(0, 0, shadowMapSize, shadowMapSize)
    // Only clear depth buffer, since we don't have a color draw buffer
    GL11C.glClear(GL11C.GL_DEPTH_BUFFER_BIT)
    GL30C.glBindVertexArray(vao)
    GL11C.glDrawArrays(GL11C.GL_TRIANGLES, 0, 6 * 6 * boxes.size)
    GL30C.glBindVertexArray(0)
    frameBuffer.unbind()

  // Render the scene normally, with sampling the previously rendered depth texture.
  def renderNormal() =
    prog2.bind()
    // Set MVP matrix of camera
    prog2.setUniform("viewProjectionMatrix", camera)
    // Set MVP matrix that was used when doing the light-render
    prog2.setUniform("lightViewProjectionMatrix", light)
    // The bias-matrix used to convert to NDC coordinates
    prog2.setUniform("biasMatrix", biasMatrix)
    // Light position and lookat for normal lambertian computation
    prog2.setUniform("lightPosition", lightPosition)
    glContext.setViewport(0, 0, width, height)
    // Must clear both color and depth, since we are re-rendering the scene
    GL11C.glClear(GL11C.GL_COLOR_BUFFER_BIT | GL11C.GL_DEPTH_BUFFER_BIT)
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, depthTexture.id) // TODO
    GL30C.glBindVertexArray(vao)
    GL11C.glDrawArrays(GL11C.GL_TRIANGLES, 0, 6 * 6 * boxes.size)
    GL30C.glBindVertexArray(0)
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, 0)
// GL20C.glUseProgram(0)
