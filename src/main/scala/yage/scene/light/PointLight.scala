package yage.scene.light

import org.joml.Vector4f

import java.nio.ByteBuffer

object PointLight:

  val byteCount = 48

class PointLight extends Light[PointLight]:

  val posW = Vector4f()
  val posV = Vector4f()

  var posWOffset = 0 // needed?
  var posVOffset = 0

  override def load(buf: ByteBuffer) =
    super.load(buf)
    posV << buf
    this

  override def store(buf: ByteBuffer) =
    super.store(buf)
    posV >> buf
    buf

  override def toString() =
    val sb = StringBuilder()
    sb.append("PointLight(\n")
    sb.append(s"  ambientColor = ${ambientColor.str}\n")
    sb.append(s"  diffuseColor = ${diffuseColor.str}\n")
    sb.append(s"  specularColor = ${specularColor.str}\n")
    sb.append(s"  posW = ${posW.str}\n")
    sb.append(s"  posV = ${posV.str}\n")
    sb.append(")")
    sb.toString()

// s"ac = $ambientColor\ndc = $diffuseColor, sc = $specularColor, pw = $posW, pv = $posV"

