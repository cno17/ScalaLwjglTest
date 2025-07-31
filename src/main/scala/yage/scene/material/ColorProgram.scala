package yage.scene.material

import yage.base.opengl.shader.Shader
import yage.base.opengl.shader.program.Program

import java.io.File

object ColorProgram:

  def apply() =
    val p = new ColorProgram()
    p.attach(Shader(resFile("Shaders/Color.vert")))
    p.attach(Shader(resFile("Shaders/Color.frag")))
    if !p.link() then throw Exception(p.infoLog)
    p

  // trait ResourceInfo
  def resDir = File(getClass.getClassLoader.getResource(".").toURI)
  def resFile(path: String) = File(resDir, path)


class ColorProgram extends Program:

  def setMaterial(m: ColorMaterial) =
    setUniform("material.color", m.color)
