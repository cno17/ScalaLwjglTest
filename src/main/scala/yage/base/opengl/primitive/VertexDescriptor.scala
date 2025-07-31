package yage.base.opengl.primitive

import yage.base.opengl.primitive.Attribute.Descriptor

class VertexDescriptor(val ads: Descriptor*):

  def attributeCount = ads.size

  def attributeByteCount(i: Int) = ads(i).format.byteCount

  def attributeOffset(i: Int) = ads.map(ad => ad.format.byteCount).take(i).sum

  def vertexByteCount = ads.map(ad => ad.format.byteCount).sum
  
  def vertexOffset(i: Int) = vertexByteCount * i
