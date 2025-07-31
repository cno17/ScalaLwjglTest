package yage.scene.material

import yage.base.opengl.shader.Shader
import yage.base.opengl.shader.program.Program
import yage.scene.light.PointLight
import yage.scene.light.SpotLight
import yage.scene.light.StarLight

import java.io.File

object LightProgram:

  def apply() =
    val p = new LightProgram()
    p.attach(Shader(resFile("Shaders/Light.vert")))
    p.attach(Shader(resFile("Shaders/Light.frag")))
    if !p.link() then throw Exception(p.infoLog)
    p

  def resDir = File(getClass.getClassLoader.getResource(".").toURI)
  def resFile(path: String) = File(resDir, path)


class LightProgram extends Program:

  def setLight(i: Int, l: PointLight) =
    val p = l.posV
    setUniform("pointLight.posV", p.x, p.y, p.z, 1f)
    setUniform("pointLight.ambientColor", l.ambientColor)
    setUniform("pointLight.diffuseColor", l.diffuseColor)
    setUniform("pointLight.specularColor", l.specularColor)

  def setLight(l: StarLight) =
    val p = l.dirV
    setUniform("starLight.dirV", p.x, p.y, p.z, 1f)
    setUniform("starLight.ambientColor", l.ambientColor)
    setUniform("starLight.diffuseColor", l.diffuseColor)
    setUniform("starLight.specularColor", l.specularColor)

  def setLight(l: SpotLight) =
    val p = l.posV
    val d = l.dirV
    setUniform("spotLight.posV", p.x, p.y, p.z, 1f)
    setUniform("spotLight.dirV", d.x, d.y, d.z, 0f)
    setUniform("spotLight.ambientColor", l.ambientColor)
    setUniform("spotLight.diffuseColor", l.diffuseColor)
    setUniform("spotLight.specularColor", l.specularColor)
  // todo

  def setMaterial(m: LightMaterial) =
    setUniform("material.diffuseColor", m.diffuseColor)
    setUniform("material.specularColor", m.specularColor)
    setUniform("material.shininess", m.shininess)
