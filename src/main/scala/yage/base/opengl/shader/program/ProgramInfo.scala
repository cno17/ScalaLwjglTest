package yage.base.opengl.shader.program

import org.lwjgl.opengl.GL43C.GL_ACTIVE_RESOURCES
import org.lwjgl.opengl.GL43C.GL_ARRAY_SIZE
import org.lwjgl.opengl.GL43C.GL_ARRAY_STRIDE
import org.lwjgl.opengl.GL43C.GL_BLOCK_INDEX
import org.lwjgl.opengl.GL43C.GL_BUFFER_BINDING
import org.lwjgl.opengl.GL43C.GL_BUFFER_DATA_SIZE
import org.lwjgl.opengl.GL43C.GL_MATRIX_STRIDE
import org.lwjgl.opengl.GL43C.GL_OFFSET
import org.lwjgl.opengl.GL43C.GL_UNIFORM
import org.lwjgl.opengl.GL43C.glGetProgramInterfacei
import org.lwjgl.opengl.GL43C.glGetProgramResourceName
import org.lwjgl.opengl.GL43C.glGetProgramResourceiv
import yage.base.opengl.shader.program.resource.Block
import yage.base.opengl.shader.program.resource.Block.Type
import yage.base.opengl.shader.program.resource.Member

import scala.collection.mutable.ArrayBuffer
import scala.language.postfixOps

trait ProgramInfo:

  this: Program =>

  def uniformNames =
    val res = ArrayBuffer[String]()
    val n = glGetProgramInterfacei(id, GL_UNIFORM, GL_ACTIVE_RESOURCES)
    for i <- 0 to n - 1 do
      res += glGetProgramResourceName(id, GL_UNIFORM, i)
    res.toArray

  def blocks(blockType: Type) =
    val bi = blockType.blockInterface
    val res = ArrayBuffer[Block]()
    val n = glGetProgramInterfacei(id, bi, GL_ACTIVE_RESOURCES)
    for i <- 0 to n - 1 do
      val b = Block()
      b.index = i
      b.name = glGetProgramResourceName(id, bi, i)
      b.typ = blockType
      b.bufferBinding = resourcePropertyValue(bi, i, GL_BUFFER_BINDING)
      b.bufferSize = resourcePropertyValue(bi, i, GL_BUFFER_DATA_SIZE)
      b.members = members(blockType, i)
      res += b
    res.toArray

  private def members(blockType: Type, blockIndex: Int) =
    val bi = blockType.blockInterface
    val mi = blockType.memberInterface
    val res = ArrayBuffer[Member]()
    val n = glGetProgramInterfacei(id, mi, GL_ACTIVE_RESOURCES)
    for i <- 0 to n - 1 do
      if resourcePropertyValue(mi, i, GL_BLOCK_INDEX) == blockIndex then
        val m = Member()
        m.index = i
        m.name = glGetProgramResourceName(id, mi, i)
        m.offset = resourcePropertyValue(mi, i, GL_OFFSET)
        m.arraySize = resourcePropertyValue(mi, i, GL_ARRAY_SIZE)
        m.arrayStride = resourcePropertyValue(mi, i, GL_ARRAY_STRIDE)
        m.matrixStride = resourcePropertyValue(mi, i, GL_MATRIX_STRIDE)
        res += m
    res.sortBy(_.offset).toArray

  private def resourcePropertyValue(programInterface: Int, resourceIndex: Int, propertyName: Int) =
    var res = 0
    useStack(s =>
      val pName = s.ints(propertyName)
      val pValue = s.ints(0)
      glGetProgramResourceiv(id, programInterface, resourceIndex, pName, null, pValue)
      res = pValue.get(0)
    )
    res
