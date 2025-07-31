package yage.base.opengl.shader.program.resource

// needed?

class Member extends Resource:

  var offset = 0
  var arraySize = 0
  var arrayStride = 0
  var matrixStride = 0

  override def toString = s"(name: $name, offset = $offset)"
  
  def toString1 = s"(name: $name, offset: $offset, arraySize = $arraySize, arrayStride = $arrayStride)"

  
  def toString2 =
    var s = ""
    s += s"index = $index\n"
    s += s"name: $name\n"
    s += s"offset: $offset\n"
    s += s"arraySize = $arraySize\n"
    s += s"arrayStride = $arrayStride\n"
    s += s"matrixStride = $matrixStride\n"
    s

