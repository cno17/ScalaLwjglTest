package yage.scene.material

import java.nio.ByteBuffer

object EmptyMaterial:

  var program: ColorProgram = null

abstract class EmptyMaterial extends Material[EmptyMaterial]:


  // override def program = null // ColorMaterial.program

  override def load(buf: ByteBuffer) = this

  override def store(buf: ByteBuffer) = buf
  
  
