package yage.base.opengl.framebuffer

import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT0
import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT1
import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT2
import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT3
import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT4
import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT5
import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT6
import org.lwjgl.opengl.GL30C.GL_COLOR_ATTACHMENT7
import org.lwjgl.opengl.GL30C.GL_DEPTH_ATTACHMENT
import org.lwjgl.opengl.GL30C.GL_DEPTH_STENCIL_ATTACHMENT
import org.lwjgl.opengl.GL30C.GL_DRAW_FRAMEBUFFER
import org.lwjgl.opengl.GL30C.GL_FRAMEBUFFER
import org.lwjgl.opengl.GL30C.GL_FRAMEBUFFER_COMPLETE
import org.lwjgl.opengl.GL30C.GL_READ_FRAMEBUFFER
import org.lwjgl.opengl.GL30C.GL_STENCIL_ATTACHMENT
import org.lwjgl.opengl.GL30C.glBindFramebuffer
import org.lwjgl.opengl.GL30C.glDeleteFramebuffers
import org.lwjgl.opengl.GL45C.glCheckNamedFramebufferStatus
import org.lwjgl.opengl.GL45C.glCreateFramebuffers
import org.lwjgl.opengl.GL45C.glNamedFramebufferTexture
import org.lwjgl.opengl.GL45C.glNamedFramebufferTextureLayer
import yage.base.Flag
import yage.base.Flag
import yage.base.Flags
import yage.base.opengl.Object
import yage.base.opengl.framebuffer.FrameBuffer.Attachment
import yage.base.opengl.resource.texture.Texture2

object FrameBuffer:

  enum Attachment(val id: Int) extends Flag:

    case Color0 extends Attachment(GL_COLOR_ATTACHMENT0)
    case Color1 extends Attachment(GL_COLOR_ATTACHMENT1)
    case Color2 extends Attachment(GL_COLOR_ATTACHMENT2)
    case Color3 extends Attachment(GL_COLOR_ATTACHMENT3)
    case Color4 extends Attachment(GL_COLOR_ATTACHMENT4)
    case Color5 extends Attachment(GL_COLOR_ATTACHMENT5)
    case Color6 extends Attachment(GL_COLOR_ATTACHMENT6)
    case Color7 extends Attachment(GL_COLOR_ATTACHMENT7)
    case Depth extends Attachment(GL_DEPTH_ATTACHMENT)
    case Stencil extends Attachment(GL_STENCIL_ATTACHMENT)
    case DepthStencil extends Attachment(GL_DEPTH_STENCIL_ATTACHMENT)

  class AttachmentFlags(fs: Attachment*) extends Flags[Attachment](fs: _*)

  enum Target(val id: Int):
    case Read extends Target(GL_READ_FRAMEBUFFER)
    case Draw extends Target(GL_DRAW_FRAMEBUFFER)
    case ReadDraw extends Target(GL_FRAMEBUFFER)


class FrameBuffer extends Object:

  override def create() = glCreateFramebuffers()

  def bind() =
    glBindFramebuffer(GL_FRAMEBUFFER, id)

  def unbind() =
    glBindFramebuffer(GL_FRAMEBUFFER, 0)

  def attach(texture: Texture2, level: Int, attachment: Attachment) =
    glNamedFramebufferTexture(id, attachment.id, texture.id, level)

  def attach(texture: Texture2, level: Int, layer: Int, attachment: Attachment) =
    glNamedFramebufferTextureLayer(id, attachment.id, texture.id, level, layer)

  def isComplete =
    glCheckNamedFramebufferStatus(id, GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE

  override def destroy() = glDeleteFramebuffers(id)


// def bindTo(target: Target) = glBindFramebuffer(target.id, id)
// def isCompleteFor(target: Target) = glCheckNamedFramebufferStatus(id, target.id) == GL_FRAMEBUFFER_COMPLETE
