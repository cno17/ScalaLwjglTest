package test.base.opengl.resource

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL31C.*
import org.lwjgl.opengl.GL44C.*
import org.lwjgl.opengl.GL45C.*
import yage.base.opengl.ClassUtil
import yage.base.opengl.resource.Resource.Access
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.MapFlag.Read
import yage.base.opengl.resource.buffer.Buffer.MapFlags
import yage.base.opengl.resource.buffer.Buffer.StorageFlag.DynamicStorage
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import yage.base.glfw.GlApp
import yage.base.glfw.window.WindowCreateInfo

object BufferTest extends GlApp:

  override def info() =
    val res = WindowCreateInfo()
    res.debugContext = true
    res

  override def init() =
    val bsf = StorageFlags()
    bsf += DynamicStorage
    val bmf = MapFlags()
    bmf += Read
    val buffer = Buffer(1024, bsf)
    // val data = BufferUtils.createByteBuffer(1024)
    // glNamedBufferSubData(id, 0, data)
    // buffer.map(bmf, 0, 1024)
    close()


/*    
  def init2() =
    println(ClassUtil.findDeclaringClassOf("GL_MAP_PERSISTENT_BIT"))
    val id = glCreateBuffers()
    glNamedBufferStorage(id, 1024, GL_DYNAMIC_STORAGE_BIT | GL_MAP_READ_BIT)
    // val data = BufferUtils.createByteBuffer(1024)
    // glNamedBufferSubData(id, 0, data)
    glMapNamedBufferRange(id, 0, 1024, GL_MAP_READ_BIT)
    close()
*/
