package demo.book.cookbook.ch04

import org.joml.Matrix4f
import yage.base.opengl.context.Capability.DepthTest
import yage.base.opengl.resource.Resource.Access.Write
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.StorageFlag.DynamicStorage
import yage.base.opengl.resource.buffer.Buffer.StorageFlag.MapCoherent
import yage.base.opengl.resource.buffer.Buffer.StorageFlag.MapPersistent
import yage.base.opengl.resource.buffer.Buffer.StorageFlag.MapWrite
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import yage.base.opengl.resource.buffer.Buffer.Target
import yage.base.opengl.shader.program.Program
import yage.base.opengl.shader.program.resource.Block.Type.Storage
import yage.base.glfw.GlApp
import yage.base.FMath.sin
import yage.scene.camera.Camera
import yage.scene.light.SpotLight
import yage.scene.material.LightMaterial
import yage.scene.primitive.Cuboid

import java.nio.ByteBuffer

// todo: find spot light bug!

object MultiSpotLightDemo extends GlApp:

  var cuboid: Cuboid = null
  var material: LightMaterial = null
  var light: SpotLight = null
  var camera: Camera = null

  var buffer: Buffer = null
  var data: ByteBuffer = null

  // var program: LightProgram = null
  var program: Program = null

  val matMW = Matrix4f()
  val matMV = Matrix4f()

  override def init() =

    cuboid = Cuboid(2f, 2f, 0.1f)

    material = LightMaterial()

    light = SpotLight()
    light.posV.set(0, 0, 10)
    light.maxAng = 0.2f
    light.angAtt = 1000f

    camera = Camera()
    camera.moveTo(0, 0, 10)

    val size = LightMaterial.byteCount + SpotLight.byteCount
    val flags = StorageFlags(DynamicStorage, MapWrite, MapPersistent, MapCoherent)
    buffer = Buffer(size, flags)
    buffer.bindTo(Target.Storage, 0)
    data = buffer.map(Write)

    program = Program(srcFile("MultiSpotLight.vert"), srcFile("MultiSpotLight.frag"))
    program.bind()
    for b <- program.blocks(Storage) do println(b)

    glContext.enable(DepthTest)
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    setUniforms()
    glContext.clear()
    cuboid.draw()

  override def step(t: Int, dt: Int) =
    val a = timer.time * 0.0001f
    val b = timer.time * 0.0002f
    val c = timer.time * 0.0003f
    material.diffuseColor.x = 0.5f + 0.5f * sin(a)
    material.diffuseColor.y = 0.5f + 0.5f * sin(b)
    material.diffuseColor.z = 0.5f + 0.5f * sin(c)
    matMW.rotateY(0.002f)
    matMV.set(camera.matWV).mul(matMW)

  private def setUniforms() =
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", camera.matVC)
    data.rewind()
    material >> data
    light >> data