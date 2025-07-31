package yage.base.opengl.resource.buffer

import org.lwjgl.opengl.GL15C.GL_ARRAY_BUFFER
import org.lwjgl.opengl.GL15C.GL_ELEMENT_ARRAY_BUFFER
import org.lwjgl.opengl.GL15C.glBindBuffer
import org.lwjgl.opengl.GL15C.glDeleteBuffers
import org.lwjgl.opengl.GL21C.GL_PIXEL_PACK_BUFFER
import org.lwjgl.opengl.GL21C.GL_PIXEL_UNPACK_BUFFER
import org.lwjgl.opengl.GL30C.GL_MAP_FLUSH_EXPLICIT_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_INVALIDATE_BUFFER_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_INVALIDATE_RANGE_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_READ_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_READ_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_WRITE_BIT
import org.lwjgl.opengl.GL30C.GL_MAP_WRITE_BIT
import org.lwjgl.opengl.GL30C.glBindBufferBase
import org.lwjgl.opengl.GL30C.glBindBufferRange
import org.lwjgl.opengl.GL31C.GL_COPY_READ_BUFFER
import org.lwjgl.opengl.GL31C.GL_COPY_WRITE_BUFFER
import org.lwjgl.opengl.GL31C.GL_TEXTURE_BUFFER
import org.lwjgl.opengl.GL31C.GL_UNIFORM_BUFFER
import org.lwjgl.opengl.GL40C.GL_DRAW_INDIRECT_BUFFER
import org.lwjgl.opengl.GL42C.GL_ATOMIC_COUNTER_BUFFER
import org.lwjgl.opengl.GL43C.GL_DISPATCH_INDIRECT_BUFFER
import org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BUFFER
import org.lwjgl.opengl.GL43C.glInvalidateBufferData
import org.lwjgl.opengl.GL43C.glInvalidateBufferSubData
import org.lwjgl.opengl.GL44C.GL_DYNAMIC_STORAGE_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_COHERENT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_COHERENT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_PERSISTENT_BIT
import org.lwjgl.opengl.GL44C.GL_MAP_PERSISTENT_BIT
import org.lwjgl.opengl.GL44C.GL_QUERY_BUFFER
import org.lwjgl.opengl.GL45C.glCreateBuffers
import org.lwjgl.opengl.GL45C.glFlushMappedNamedBufferRange
import org.lwjgl.opengl.GL45C.glGetNamedBufferSubData
import org.lwjgl.opengl.GL45C.glMapNamedBuffer
import org.lwjgl.opengl.GL45C.glMapNamedBufferRange
import org.lwjgl.opengl.GL45C.glNamedBufferStorage
import org.lwjgl.opengl.GL45C.glNamedBufferSubData
import org.lwjgl.opengl.GL45C.glUnmapNamedBuffer
import yage.base.ByteBufferUtil
import yage.base.Flag
import yage.base.Flag
import yage.base.Flags
import yage.base.Flags
import yage.base.opengl.resource.Resource
import yage.base.opengl.resource.Resource.Access
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import yage.base.opengl.resource.buffer.Buffer.Target

import java.nio.ByteBuffer

object Buffer extends ByteBufferUtil:

  enum MapFlag(val id: Int) extends Flag:

    case Read extends MapFlag(GL_MAP_READ_BIT)
    case Write extends MapFlag(GL_MAP_WRITE_BIT)
    case Persistent extends MapFlag(GL_MAP_PERSISTENT_BIT)
    case Coherent extends MapFlag(GL_MAP_COHERENT_BIT)
    case InvalidateRange extends MapFlag(GL_MAP_INVALIDATE_RANGE_BIT)
    case InvalidateBuffer extends MapFlag(GL_MAP_INVALIDATE_BUFFER_BIT)
    case FlushExplicit extends MapFlag(GL_MAP_FLUSH_EXPLICIT_BIT)

  class MapFlags(fs: MapFlag*) extends Flags[MapFlag](fs: _*)


  enum StorageFlag(val id: Int) extends Flag:

    case DynamicStorage extends StorageFlag(GL_DYNAMIC_STORAGE_BIT)
    case MapRead extends StorageFlag(GL_MAP_READ_BIT)
    case MapWrite extends StorageFlag(GL_MAP_WRITE_BIT)
    case MapPersistent extends StorageFlag(GL_MAP_PERSISTENT_BIT)
    case MapCoherent extends StorageFlag(GL_MAP_COHERENT_BIT)

  class StorageFlags(fs: StorageFlag*) extends Flags[StorageFlag](fs: _*)


  enum Target(val value: Int):

    case Vertex extends Target(GL_ARRAY_BUFFER)
    case Index extends Target(GL_ELEMENT_ARRAY_BUFFER)
    case Uniform extends Target(GL_UNIFORM_BUFFER)
    case Storage extends Target(GL_SHADER_STORAGE_BUFFER)
    case Counter extends Target(GL_ATOMIC_COUNTER_BUFFER)
    case Texture extends Target(GL_TEXTURE_BUFFER)
    case DrawIndirect extends Target(GL_DRAW_INDIRECT_BUFFER)
    case DispatchIndirect extends Target(GL_DISPATCH_INDIRECT_BUFFER)
    case CopyRead extends Target(GL_COPY_READ_BUFFER)
    case CopyWrite extends Target(GL_COPY_WRITE_BUFFER)
    case PixelPack extends Target(GL_PIXEL_PACK_BUFFER)
    case PixelUnpack extends Target(GL_PIXEL_UNPACK_BUFFER)
    case Query extends Target(GL_QUERY_BUFFER)


  def apply(size: Long, flags: StorageFlags) =
    val res = new Buffer()
    res.allocate(size, flags)
    res

  def apply(data: ByteBuffer, flags: StorageFlags) =
    val res = new Buffer()
    res.allocate(data.rewind(), flags)
    res

/**
 * Todo:
 *   - clear, copy
 *   - c++ stream operators: <<, >>
 *
 */

class Buffer extends Resource, BufferInfo:

  override def create() = glCreateBuffers()

  def allocate(size: Long, flags: StorageFlags) =
    glNamedBufferStorage(id, size, flags.value)

  def allocate(data: ByteBuffer, flags: StorageFlags) =
    glNamedBufferStorage(id, data, flags.value)

  def clear() = 0

  def load(off: Long, data: ByteBuffer) =
    glNamedBufferSubData(id, off, data)

  def store(off: Long, data: ByteBuffer) =
    glGetNamedBufferSubData(id, off, data)

  def invalidate() =
    glInvalidateBufferData(id);

  def invalidate(off: Long, ext: Long) =
    glInvalidateBufferSubData(id, off, ext);

  def bindTo(t: Target) =
    glBindBuffer(t.value, id)

  def bindTo(t: Target, i: Int) =
    glBindBufferBase(t.value, i, id)

  def bindTo(t: Target, i: Int, off: Long, ext: Long) =
    glBindBufferRange(t.value, i, id, off, ext)

  def unbindFrom(t: Target) =
    glBindBuffer(t.value, 0)

  def map(a: Access) =
    glMapNamedBuffer(id, a.id)

  def map(a: Access, off: Long, ext: Long) =
    glMapNamedBufferRange(id, off, ext, a.id)

  def unmap() =
    glUnmapNamedBuffer(id)

  def flush(off: Long = 0, ext: Long = size) =
    glFlushMappedNamedBufferRange(id, off, ext)

  //

  override def destroy() = glDeleteBuffers(id)

  def <<(off: Long, data: ByteBuffer) = load(off, data)
  def >>(off: Long, data: ByteBuffer) = store(off, data)

/* 
 *
 * def clear(off: Long, ext: Long) =
 * val internalFormat = 0 val format = 0 val typ = 0 glClearNamedBufferData(id, internalFormat,
 * format, typ, null.asInstanceOf[ByteBuffer]) 
 * 
 * */
