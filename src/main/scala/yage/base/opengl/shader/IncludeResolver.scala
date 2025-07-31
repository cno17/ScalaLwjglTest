package yage.base.opengl.shader

import yage.base.FileExt
import java.io.File

object IncludeResolver extends FileExt:

  def resolveImports(f: File) =
    val res = StringBuilder()
    for l <- f.lines do
      if l.startsWith("#include") then
        val a = l.indexOf("\"") + 1
        val b = l.indexOf("\"", a)
        // val f2 = File(shaderDir, l.substring(a, b))
        // println(f2.getAbsolutePath)
        // println(f2.exists())
        res.append(File(shaderDir, l.substring(a, b)).chars)
      else res.append(l + "\n")
    res.toString

  // how to replace an import statement with the chars of the referenced file?

  def shaderDir =
    val p1 = File(getClass.getResource(".").toURI).getAbsolutePath
    val p2 = getClass.getPackage.getName.replace('.', '\\')
    File(p1.replace(p2, "") + "Shaders\\")

  def srcDir =
    val p = getClass.getPackage.getName.replace('.', '\\')
    File(getClass.getClassLoader.getResource(p).toURI)


  @main
  def test() =
    // val f = File(s"${shaderDir}PhongBlinn.glsl")
    println(resolveImports(File(srcDir, "Shader.vert")))
