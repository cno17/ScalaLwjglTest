package yage.base

import java.nio.ByteBuffer

trait ByteBufferExt:

  extension (buf: ByteBuffer)
    

    def put(vs: Array[Float]) = for v <- vs do buf.putFloat(v)
