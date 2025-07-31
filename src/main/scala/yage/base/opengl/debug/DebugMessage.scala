package yage.base.opengl.debug

import org.lwjgl.opengl.GL11C.GL_DONT_CARE
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_HIGH
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_LOW
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_MEDIUM
import org.lwjgl.opengl.GL43C.GL_DEBUG_SEVERITY_NOTIFICATION
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_API
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_APPLICATION
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_OTHER
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_SHADER_COMPILER
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_THIRD_PARTY
import org.lwjgl.opengl.GL43C.GL_DEBUG_SOURCE_WINDOW_SYSTEM
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_ERROR
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_MARKER
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_OTHER
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_PERFORMANCE
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_POP_GROUP
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_PORTABILITY
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_PUSH_GROUP
import org.lwjgl.opengl.GL43C.GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR
import yage.base.Flag
import yage.base.FlagFinder
import yage.base.opengl.debug.DebugMessage.Severity
import yage.base.opengl.debug.DebugMessage.Source
import yage.base.opengl.debug.DebugMessage.Type

object DebugMessage:

  object Severity extends FlagFinder[Severity]
    
  enum Severity(val id: Int, val level: Int) extends Flag, Ordered[Severity]:

    case Notification extends Severity(GL_DEBUG_SEVERITY_NOTIFICATION, 0)
    case Low extends Severity(GL_DEBUG_SEVERITY_LOW, 1)
    case Medium extends Severity(GL_DEBUG_SEVERITY_MEDIUM, 2)
    case High extends Severity(GL_DEBUG_SEVERITY_HIGH, 3)
    case DontCare extends Severity(GL_DONT_CARE, 17)

    override def compare(s: Severity) = level - s.level

  object Source extends FlagFinder[Source]

  enum Source(val id: Int) extends Flag:

    case Api extends Source(GL_DEBUG_SOURCE_API)
    case WindowSystem extends Source(GL_DEBUG_SOURCE_WINDOW_SYSTEM)
    case ShaderCompiler extends Source(GL_DEBUG_SOURCE_SHADER_COMPILER)
    case ThirdParty extends Source(GL_DEBUG_SOURCE_THIRD_PARTY)
    case Application extends Source(GL_DEBUG_SOURCE_APPLICATION)
    case Other extends Source(GL_DEBUG_SOURCE_OTHER)
    case DontCare extends Source(GL_DONT_CARE)

  object Type extends FlagFinder[Type]

  enum Type(val id: Int) extends Flag:

    case Error extends Type(GL_DEBUG_TYPE_ERROR)
    case DeprecatedBehavior extends Type(GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR)
    case UndefinedBehavior extends Type(GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR)
    case Portability extends Type(GL_DEBUG_TYPE_PORTABILITY)
    case Performance extends Type(GL_DEBUG_TYPE_PERFORMANCE)
    case Marker extends Type(GL_DEBUG_TYPE_MARKER)
    case PushGroup extends Type(GL_DEBUG_TYPE_PUSH_GROUP)
    case PopGroup extends Type(GL_DEBUG_TYPE_POP_GROUP)
    case Other extends Type(GL_DEBUG_TYPE_OTHER)
    case DontCare extends Type(GL_DONT_CARE)


/**
 * A debug message in the debug message stream is uniquely identified 
 * by the (source, typ, id) triple.
 */

class DebugMessage:

  var source = Source.Api
  var typ = Type.Error
  var id = 0
  var severity = Severity.High
  var text = ""

  override def toString =
    s"Source = $source, Type = $typ, Id = $id, Severity = $severity\n$text\n"
