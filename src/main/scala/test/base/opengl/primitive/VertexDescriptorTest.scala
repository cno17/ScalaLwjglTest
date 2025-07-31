package test.base.opengl.primitive

import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_2
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.Color
import yage.base.opengl.primitive.Attribute.Semantics.Position
import yage.base.opengl.primitive.VertexDescriptor

object VertexDescriptorTest:

  def main(args: Array[String]) =
    val ad0 = Descriptor(0, F32_2, Position)
    val ad1 = Descriptor(1, F32_4, Color)
    val vd = VertexDescriptor(ad0, ad1)
    println(vd.attributeOffset(0))
    println(vd.attributeOffset(1))
    println(vd.vertexByteCount)
