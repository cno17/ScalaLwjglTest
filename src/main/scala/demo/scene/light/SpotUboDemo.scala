package demo.scene.light

import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import yage.base.opengl.resource.buffer.Buffer.Target.Uniform
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.light.SpotLight
import yage.scene.material.LightMaterial
import yage.scene.primitive.Cuboid
import yage.scene.primitive.Spheroid
import yage.scene.primitive.Torus
import yage.base.FMath.Pi

import java.io.File

object SpotUboDemo extends GlApp:

  var material: LightMaterial = null
  var lights: Array[SpotLight] = null
  // var bufferData
  var buffer: Buffer = null
  var program: Program = null
  var primitive: Primitive = null

  val matVW = Matrix4f().translate(0.0f, 0.0f, 10.0f)
  val matWV = Matrix4f(matVW).invert()
  val matVC = Matrix4f().perspective(Pi / 4, 4f / 3f, 0.1f, 100f)
  val matMW = Matrix4f()
  val matMV = Matrix4f(matWV).mul(matMW)

  override def init() =
    material = LightMaterial()
    material.diffuseColor.set(0.8f, 0.8f, 1.0f)

    lights = Array(SpotLight(), SpotLight())
    lights(0).posV.set(-0.6f, 0, 0)
    lights(0).maxAng = 0.12f
    lights(0).angAtt = 128
    lights(1).posV.set(+1.2f, 0, 0)
    lights(1).maxAng = 0.18f
    lights(1).angAtt = 128

    buffer = Buffer(createBufferData(), StorageFlags())
    buffer.bindTo(Uniform)

    primitive = Torus(0.5f, 3.0f, 32, 16)
    primitive = Spheroid(3f, 2f, 1f, 32, 16)
    primitive = Cuboid(3f, 3f, 0.1f)

    program = Program(srcFile("Spot.vert"), srcFile("SpotUbo.frag"))
    program.bind()

    // for v <- program.uniformNames do println(v)
    // System.exit(0)

    // setLight(0)
    // setLight(1)
    // setMaterial(material)


    glEnable(GL_DEPTH_TEST)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
    program.setUniform("matMV", matMV)
    program.setUniform("matVC", matVC)
    primitive.draw()

  override def step(t: Int, dt: Int) =
    val ax = timer.time.toFloat * 0.005f
    val ay = timer.time.toFloat * 0.017f
    val az = timer.time.toFloat * 0.029f
    // dInt.x = 0.5f + 0.5f * sin(ax)
    // dInt.y = 0.5f + 0.5f * sin(ay)
    // dInt.z = 0.5f + 0.5f * sin(az)
    matMW.rotateX(0.002f)
    matMW.rotateY(0.003f)
    matMW.rotateZ(0.005f)
    matMV.set(matWV).mul(matMW)


  def createBufferData() =
    val size = LightMaterial.byteCount + 2 * SpotLight.byteCount
    val data = BufferUtils.createByteBuffer(size)
    var off = 0
    material >> (data, off)
    off += LightMaterial.byteCount
    lights(0) >> (data, off)
    off += SpotLight.byteCount
    lights(1) >> (data, off)
    data








  def setLight(i: Int) =
    val l = lights(i)
    program.setUniform(s"lights[$i].posV", l.posV.x, l.posV.y, l.posV.z, 1)
    program.setUniform(s"lights[$i].dirV", l.dirV.x, l.dirV.y, l.dirV.z, 0)
    program.setUniform(s"lights[$i].maxAng", l.maxAng)
    program.setUniform(s"lights[$i].angAtt", l.angAtt)
    program.setUniform(s"lights[$i].ambient", l.ambientColor)
    program.setUniform(s"lights[$i].diffuse", l.diffuseColor)
    program.setUniform(s"lights[$i].specular", l.specularColor)

  def setMaterial(m: LightMaterial) =
    program.setUniform("material.diffuse", m.diffuseColor)
    program.setUniform("material.specular", m.specularColor)
    program.setUniform("material.shininess", m.shininess)
