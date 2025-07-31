package yage.base.opengl.context

import org.lwjgl.opengl.GL11C.*

enum Capability(val id: Int):

  case Blend extends Capability(GL_BLEND)
  case ColorLogicOp extends Capability(GL_COLOR_LOGIC_OP)
  case CullFace extends Capability(GL_CULL_FACE)
  //case DebugOutput extends Capability(GL_DEBUG_OUTPUT)
  //case DepthClamp extends Capability(GL_DEPTH_CLAMP)
  case DepthTest extends Capability(GL_DEPTH_TEST)
  case Dither extends Capability(GL_DITHER)
  case LineSmooth extends Capability(GL_LINE_SMOOTH)
  //case Multisample extends Capability(GL_MULTISAMPLE)
  case PolygonOffsetFill extends Capability(GL_POLYGON_OFFSET_FILL)
  case PolygonOffsetLine extends Capability(GL_POLYGON_OFFSET_LINE)
  case PolygonOffsetPoint extends Capability(GL_POLYGON_OFFSET_POINT)
  case PolygonSmooth extends Capability(GL_POLYGON_SMOOTH)
  //case PrimitiveRestart extends Capability(GL_PRIMITIVE_RESTART)
  // ...
  case ScissorTest extends Capability(GL_SCISSOR_TEST)
  case StencilTest extends Capability(GL_STENCIL_TEST)
