package yage.base.opengl.resource

class Range(val n: Int):

  val off = new Array[Int](n)
  val ext = new Array[Int](n)

  def ox = off(0)
  def oy = off(1)
  def oz = off(2)
  def ex = ext(0)
  def ey = ext(1)
  def ez = ext(2)

  def ox_=(x: Int) = off(0) = x
  def oy_=(y: Int) = off(1) = y
  def oz_=(z: Int) = off(2) = z
  def ex_=(x: Int) = ext(0) = x
  def ey_=(y: Int) = ext(1) = y
  def ez_=(z: Int) = ext(2) = z

class Range1(ox: Int, ex: Int) extends Range(1):

  def this(ex: Int) = this(0, ex)

  set(ox, ex)

  def set(ox: Int, ex: Int) =
    off(0) = ox
    ext(0) = ex


class Range2(ox: Int, oy: Int, ex: Int, ey: Int) extends Range(2):

  def this(ex: Int, ey: Int) = this(0, 0, ex, ey)

  set(ox, oy, ex, ey)

  def set(ox: Int, oy: Int, ex: Int, ey: Int) =
    off(0) = ox
    off(1) = oy
    ext(0) = ex
    ext(1) = ey

class Range3(ox: Int, oy: Int, oz: Int, ex: Int, ey: Int, ez: Int) extends Range(3):

  def this(ex: Int, ey: Int, ez: Int) = this(0, 0, 0, ex, ey, ez)

  set(ox, oy, oz, ex, ey, ez)

  def set(ox: Int, oy: Int, oz: Int, ex: Int, ey: Int, ez: Int) =
    off(0) = ox
    off(1) = oy
    off(2) = oz
    ext(0) = ex
    ext(1) = ey
    ext(2) = ez

