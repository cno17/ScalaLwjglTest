package yage.base.opengl.shader

import org.lwjgl.opengl.GL11C.GL_TRUE
import org.lwjgl.opengl.GL20C.GL_COMPILE_STATUS
import org.lwjgl.opengl.GL20C.GL_FRAGMENT_SHADER
import org.lwjgl.opengl.GL20C.GL_VERTEX_SHADER
import org.lwjgl.opengl.GL20C.glCompileShader
import org.lwjgl.opengl.GL20C.glCreateShader
import org.lwjgl.opengl.GL20C.glDeleteShader
import org.lwjgl.opengl.GL20C.glGetShaderInfoLog
import org.lwjgl.opengl.GL20C.glGetShaderi
import org.lwjgl.opengl.GL20C.glShaderSource
import org.lwjgl.opengl.GL32C.GL_GEOMETRY_SHADER
import org.lwjgl.opengl.GL40C.GL_TESS_CONTROL_SHADER
import org.lwjgl.opengl.GL40C.GL_TESS_EVALUATION_SHADER
import org.lwjgl.opengl.GL43C.GL_COMPUTE_SHADER
import yage.base.FileExt
import yage.base.Flag
import yage.base.Flags
import yage.base.opengl.Object
import yage.base.opengl.shader.Shader.Stage

import java.io.File

object Shader extends FileExt:

  object Stage:

    def apply(id: Int) = Stage.values.find(_.id == id).get
    def apply(ext: String) = Stage.values.find(_.ext == ext).get

  enum Stage(val id: Int, val ext: String) extends Flag:

    case Vertex extends Stage(GL_VERTEX_SHADER, "vert")
    case TessControl extends Stage(GL_TESS_CONTROL_SHADER, "tesc")
    case TessEvaluation extends Stage(GL_TESS_EVALUATION_SHADER, "tese")
    case Geometry extends Stage(GL_GEOMETRY_SHADER, "geom")
    case Fragment extends Stage(GL_FRAGMENT_SHADER, "frag")
    case Compute extends Stage(GL_COMPUTE_SHADER, "comp")

  class StageFlags extends Flags[Stage]

  def apply(f: File) =
    val s = new Shader(Stage(f.extension))
    s.load(resolveImports(f))
    if !s.compile() then throw Exception(f.getName() + "\n" + s.infoLog)
    s

  private def shaderDir =
    val p1 = File(getClass.getResource(".").toURI).getAbsolutePath
    val p2 = getClass.getPackage.getName.replace('.', '\\')
    File(p1.replace(p2, "") + "Shaders\\")

  private def resolveImports(f: File) =
    val res = StringBuilder()
    for l <- f.lines do
      if l.startsWith("#include") then
        val a = l.indexOf("\"") + 1
        val b = l.indexOf("\"", a)
        res.append(File(shaderDir, l.substring(a, b)).chars)
      else res.append(l + "\n")
    res.toString

class Shader(s: Stage) extends Object:
  
  def load(code: String) = glShaderSource(id, code)

  def compile() =
    glCompileShader(id)
    glGetShaderi(id, GL_COMPILE_STATUS) == GL_TRUE

  def infoLog = glGetShaderInfoLog(id)

  override def create() = glCreateShader(s.id)
  
  override def destroy() = glDeleteShader(id)


/*  object Shader:
 *
 * def apply(stage: Stage, source: String) =
 * val res = new Shader(stage) res.load(source) res.compile() if !res.isCompiled then throw
 * Exception(res.infoLog) res
 *
 * def apply(stage: Stage, code: ByteBuffer) =
 * val res = new Shader(stage) res.load(code) res.specialize() if !res.isSpecialized then throw
 * Exception(res.infoLog) res def load(code: ByteBuffer) =
 * useStack(s => val pId = s.ints(id); glShaderBinary(pId, GL_SHADER_BINARY_FORMAT_SPIR_V, code) )
 *
 * def specialize() =
 * val index: IntBuffer = null val value: IntBuffer = null glSpecializeShader(id, "main", index,
 * value)
 *
 * def isSpecialized = glGetShaderi(id, GL_COMPILE_STATUS) == GL_TRUE */
