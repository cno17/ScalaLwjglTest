package demo.scene.shadow

import org.joml.Matrix4f
import org.joml.Vector3f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.*
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.window.WindowCreateInfo

import java.io.File
import java.nio.ByteBuffer
import java.nio.FloatBuffer

/** Simple demo to showcase shadow mapping with a custom shader doing perspective divide and depth test (i.e. no
 * sampler2DShadow).
 *
 * @author
 * Kai Burjack
 */

object ShadowMappingDemo3 extends GlApp:

  val boxes = Scene.boxes2
  val UP = Vector3f(0.0f, 1.0f, 0.0f)
  var shadowMapSize: Int = 1024
  var lightPosition: Vector3f = Vector3f(6.0f, 3.0f, 6.0f)
  var lightLookAt: Vector3f = Vector3f(0.0f, 1.0f, 0.0f)
  var cameraPosition: Vector3f = Vector3f(-3.0f, 6.0f, 6.0f)
  var cameraLookAt: Vector3f = Vector3f(0.0f, 0.0f, 0.0f)
  var lightDistance: Float = 10.0f
  var lightHeight: Float = 4.0f

  var width = 1200
  var height = 800

  var vao: Int = 0
  var vbo: Int = 0

  var shadowProgram: Program = null
  var shadowProgramVPUniform: Int = 0

  var normalProgram: Program = null
  var normalProgramBiasUniform: Int = 0
  var normalProgramVPUniform: Int = 0
  var normalProgramLVPUniform: Int = 0
  var normalProgramLightPosition: Int = 0
  var normalProgramLightLookAt: Int = 0

  var fbo: Int = 0
  var depthTexture: Int = 0
  var samplerLocation: Int = 0

  var matrixBuffer = BufferUtils.createFloatBuffer(16)

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
    GL11C.glEnable(GL11C.GL_CULL_FACE)
    GL11C.glEnable(GL11C.GL_DEPTH_TEST)
    GL11C.glClearColor(0.2f, 0.3f, 0.4f, 1.0f)
    // Create all needed GL resources
    createVao()
    createShadowProgram()
    createNormalProgram()
    createDepthTexture()
    createFbo()

  // Create the texture storing the depth values of the light-render.
  def createDepthTexture() =
    depthTexture = GL11C.glGenTextures()
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, depthTexture)
    GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MIN_FILTER, GL11C.GL_NEAREST)
    GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_MAG_FILTER, GL11C.GL_NEAREST)
    GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_S, GL12C.GL_CLAMP_TO_EDGE)
    GL11C.glTexParameteri(GL11C.GL_TEXTURE_2D, GL11C.GL_TEXTURE_WRAP_T, GL12C.GL_CLAMP_TO_EDGE)
    GL11C.glTexImage2D(
      GL11C.GL_TEXTURE_2D,
      0,
      GL11C.GL_DEPTH_COMPONENT,
      shadowMapSize,
      shadowMapSize,
      0,
      GL11C.GL_DEPTH_COMPONENT,
      GL11C.GL_UNSIGNED_BYTE,
      null.asInstanceOf[ByteBuffer]
    )
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, 0)

  // Create the FBO to render the depth values of the light-render into the depth texture.
  def createFbo() =
    fbo = GL30C.glGenFramebuffers()
    GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, fbo)
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, depthTexture)
    GL11C.glDrawBuffer(GL11C.GL_NONE)
    GL11C.glReadBuffer(GL11C.GL_NONE)
    GL30C.glFramebufferTexture2D(
      GL30C.GL_FRAMEBUFFER,
      GL30C.GL_DEPTH_ATTACHMENT,
      GL11C.GL_TEXTURE_2D,
      depthTexture,
      0
    )
    val fboStatus = GL30C.glCheckFramebufferStatus(GL30C.GL_FRAMEBUFFER)
    if (fboStatus != GL30C.GL_FRAMEBUFFER_COMPLETE)
      throw AssertionError("Could not create FBO: $fboStatus")

    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, 0)
    GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, 0)

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

  def createShadowProgram() =
    // needs wor: resource loading!
    val vf = File(srcDir, "ShadowMapping.vert")
    val ff = File(srcDir, "ShadowMapping.frag")
    shadowProgram = Program(vf, ff)
    shadowProgram.bind()
    shadowProgramVPUniform = GL20C.glGetUniformLocation(shadowProgram.id, "viewProjectionMatrix")
    GL20C.glUseProgram(0)

  def createNormalProgram() =
    val vf = File(srcDir, "ShadowMappingShade.vert")
    val ff = File(srcDir, "ShadowMappingShade.frag")
    normalProgram = Program(vf, ff)
    normalProgram.bind()
    samplerLocation = GL20C.glGetUniformLocation(normalProgram.id, "depthTexture")
    normalProgramBiasUniform = GL20C.glGetUniformLocation(normalProgram.id, "biasMatrix")
    normalProgramVPUniform = GL20C.glGetUniformLocation(normalProgram.id, "viewProjectionMatrix")
    normalProgramLVPUniform = GL20C.glGetUniformLocation(normalProgram.id, "lightViewProjectionMatrix")
    normalProgramLightPosition = GL20C.glGetUniformLocation(normalProgram.id, "lightPosition")
    normalProgramLightLookAt = GL20C.glGetUniformLocation(normalProgram.id, "lightLookAt")
    GL20C.glUniform1i(samplerLocation, 0)
    GL20C.glUseProgram(0)

  // Update the camera MVP matrix.
  override def step(t: Int, dt: Int) =
    // Update light
    val alpha = System.currentTimeMillis() / 1000.0 * 0.5
    val x = Math.sin(alpha).toFloat
    val z = Math.cos(alpha).toFloat
    lightPosition.set(lightDistance * x, 3 + Math.sin(alpha).toFloat, lightDistance * z)
    // light.setPerspective(Math.toRadians(45.0).toFloat(), 1.0f, 0.1f, 60.0f).lookAt(lightPosition, lightLookAt, UP)
    light.setPerspective(Math.PI.toFloat / 4, 1.0f, 0.1f, 60.0f).lookAt(lightPosition, lightLookAt, UP)
    // Update camera
    camera
      .setPerspective(Math.PI.toFloat / 4, width.toFloat / height, 0.1f, 30.0f)
      .lookAt(cameraPosition, cameraLookAt, UP)

  override def draw() =
    renderShadowMap()
    renderNormal()

  // Render the shadow map into a depth texture.
  def renderShadowMap() =
    GL20C.glUseProgram(shadowProgram.id)
    // Set MVP matrix of the "light camera"
    GL20C.glUniformMatrix4fv(shadowProgramVPUniform, false, light.get(matrixBuffer))
    GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, fbo)
    GL11C.glViewport(0, 0, shadowMapSize, shadowMapSize)
    // Only clear depth buffer, since we don't have a color draw buffer
    GL11C.glClear(GL11C.GL_DEPTH_BUFFER_BIT)
    GL30C.glBindVertexArray(vao)
    GL11C.glDrawArrays(GL11C.GL_TRIANGLES, 0, 6 * 6 * boxes.size)
    GL30C.glBindVertexArray(0)
    GL30C.glBindFramebuffer(GL30C.GL_FRAMEBUFFER, 0)
    GL20C.glUseProgram(0)

  // Render the scene normally, with sampling the previously rendered depth texture.
  def renderNormal() =
    GL20C.glUseProgram(normalProgram.id)
    // Set MVP matrix of camera
    GL20C.glUniformMatrix4fv(normalProgramVPUniform, false, camera.get(matrixBuffer))
    // Set MVP matrix that was used when doing the light-render
    GL20C.glUniformMatrix4fv(normalProgramLVPUniform, false, light.get(matrixBuffer))
    // The bias-matrix used to convert to NDC coordinates
    GL20C.glUniformMatrix4fv(normalProgramBiasUniform, false, biasMatrix.get(matrixBuffer))
    // Light position and lookat for normal lambertian computation
    GL20C.glUniform3f(normalProgramLightPosition, lightPosition.x, lightPosition.y, lightPosition.z)
    GL20C.glUniform3f(normalProgramLightLookAt, lightLookAt.x, lightLookAt.y, lightLookAt.z)
    GL11C.glViewport(0, 0, width, height)
    // Must clear both color and depth, since we are re-rendering the scene
    GL11C.glClear(GL11C.GL_COLOR_BUFFER_BIT | GL11C.GL_DEPTH_BUFFER_BIT)
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, depthTexture)
    GL30C.glBindVertexArray(vao)
    GL11C.glDrawArrays(GL11C.GL_TRIANGLES, 0, 6 * 6 * boxes.size)
    GL30C.glBindVertexArray(0)
    GL11C.glBindTexture(GL11C.GL_TEXTURE_2D, 0)
    GL20C.glUseProgram(0)
