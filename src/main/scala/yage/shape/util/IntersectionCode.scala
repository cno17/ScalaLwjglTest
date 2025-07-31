package yage.shape.util

import org.joml.Intersectionf.*
import org.joml.Vector2f

object IntersectionCode:
  def apply(id: Int) = IntersectionCode.values.find(_.id == id).get

enum IntersectionCode(val id: Int):
  case Inside extends IntersectionCode(INSIDE)
  case Outside extends IntersectionCode(OUTSIDE)
  case OneIntersection extends IntersectionCode(ONE_INTERSECTION)
  case TwoIntersection extends IntersectionCode(TWO_INTERSECTION)

class IResult2:
  var num = 0
  val p1 = Vector2f()
  val p2 = Vector2f()
  // inside, outside info
  