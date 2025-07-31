package yage.base.opengl.resource.texture

import org.lwjgl.opengl.GL11C.GL_LINEAR
import org.lwjgl.opengl.GL11C.GL_LINEAR_MIPMAP_LINEAR
import org.lwjgl.opengl.GL11C.GL_LINEAR_MIPMAP_NEAREST
import org.lwjgl.opengl.GL11C.GL_NEAREST
import org.lwjgl.opengl.GL11C.GL_NEAREST_MIPMAP_LINEAR
import org.lwjgl.opengl.GL11C.GL_NEAREST_MIPMAP_NEAREST
import org.lwjgl.opengl.GL11C.GL_REPEAT
import org.lwjgl.opengl.GL11C.GL_TEXTURE_1D
import org.lwjgl.opengl.GL11C.GL_TEXTURE_2D
import org.lwjgl.opengl.GL11C.glDeleteTextures
import org.lwjgl.opengl.GL12C.GL_CLAMP_TO_EDGE
import org.lwjgl.opengl.GL12C.GL_TEXTURE_3D
import org.lwjgl.opengl.GL13C.GL_CLAMP_TO_BORDER
import org.lwjgl.opengl.GL13C.GL_TEXTURE_CUBE_MAP
import org.lwjgl.opengl.GL14C.GL_MIRRORED_REPEAT
import org.lwjgl.opengl.GL30C.GL_TEXTURE_1D_ARRAY
import org.lwjgl.opengl.GL30C.GL_TEXTURE_2D_ARRAY
import org.lwjgl.opengl.GL31C.GL_TEXTURE_BUFFER
import org.lwjgl.opengl.GL40C.GL_TEXTURE_CUBE_MAP_ARRAY
import org.lwjgl.opengl.GL42C.glBindImageTexture
import org.lwjgl.opengl.GL44C.GL_MIRROR_CLAMP_TO_EDGE
import org.lwjgl.opengl.GL45C.glBindTextureUnit
import org.lwjgl.opengl.GL45C.glGenerateTextureMipmap
import yage.base.opengl.resource.Resource
import yage.base.opengl.resource.Resource.Access
import yage.base.opengl.resource.texture.Texture.Type

object Texture:

  enum MagFilter(val id: Int):

    case Nearest extends MagFilter(GL_NEAREST)
    case Linear extends MagFilter(GL_LINEAR)

  enum MinFilter(val id: Int):

    case Nearest extends MinFilter(GL_NEAREST)
    case NearestMipmapNearest extends MinFilter(GL_NEAREST_MIPMAP_NEAREST)
    case NearestMipmapLinear extends MinFilter(GL_NEAREST_MIPMAP_LINEAR)
    case Linear extends MinFilter(GL_LINEAR)
    case LinearMipmapNearest extends MinFilter(GL_LINEAR_MIPMAP_NEAREST)
    case LinearMipmapLinear extends MinFilter(GL_LINEAR_MIPMAP_LINEAR)

  enum Type(val value: Int):

    case Dim1 extends Type(GL_TEXTURE_1D)
    case Dim1Array extends Type(GL_TEXTURE_1D_ARRAY)
    case Dim2 extends Type(GL_TEXTURE_2D)
    case Dim2Array extends Type(GL_TEXTURE_2D_ARRAY)
    case Dim3 extends Type(GL_TEXTURE_3D)
    case Cube extends Type(GL_TEXTURE_CUBE_MAP)
    case CubeArray extends Type(GL_TEXTURE_CUBE_MAP_ARRAY)
    case Buffer extends Type(GL_TEXTURE_BUFFER)

  enum WrapMode(val id: Int):

    case ClampToEdge extends WrapMode(GL_CLAMP_TO_EDGE)
    case ClampToBorder extends WrapMode(GL_CLAMP_TO_BORDER)
    case MirroredRepeat extends WrapMode(GL_MIRRORED_REPEAT)
    case Repeat extends WrapMode(GL_REPEAT)
    case MirroredClampToEdge extends WrapMode(GL_MIRROR_CLAMP_TO_EDGE)

abstract class Texture(
  val typ: Type,
  val texelFormat: TexelFormat,
  val numLevels: Int,
  val numLayers: Int)
  extends Resource, TextureInfo, TextureSampler:

  var textureUnitBinding = 0
  var imageUnitBinding = 0

  def bindToTextureUnit(i: Int) =
    glBindTextureUnit(i, id)
    textureUnitBinding = i

  def bindToImageUnit(i: Int, a: Access, f: TexelFormat) =
    glBindImageTexture(i, id, 0, true, 0, a.id, f.id)
    imageUnitBinding = i

  // bind level
  def bindToImageUnit(i: Int, a: Access, f: TexelFormat, level: Int): Unit =
    glBindImageTexture(i, id, level, true, 0, a.id, f.id)
    imageUnitBinding = i

  // bind layer of level
  def bindToImageUnit(i: Int, a: Access, f: TexelFormat, level: Int, layer: Int): Unit =
    glBindImageTexture(i, id, level, false, layer, a.id, f.id)
    imageUnitBinding = i

  def createMipMap() =
    glGenerateTextureMipmap(id)

  override def destroy() = glDeleteTextures(id)
