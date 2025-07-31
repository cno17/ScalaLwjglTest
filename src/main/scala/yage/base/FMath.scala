package yage.base

import org.joml.Vector2f

object FMath:

  val Pi = math.Pi.toFloat
  val Rnd = scala.util.Random() 
  
  def abs(x: Int) = math.abs(x)
  def abs(x: Float) = math.abs(x)
  def acos(x: Float) = math.acos(x).toFloat
  def asin(x: Float) = math.asin(x).toFloat
  def atan(x: Float) = math.atan(x).toFloat
  def ceil(x: Float) = math.ceil(x).toInt
  def cos(x: Float) = math.cos(x).toFloat
  def floor(x: Float) = math.floor(x).toInt
  def fract(x: Float) = x - floor(x)
  def max(x: Int, y: Int) = math.max(x, y)
  def max(x: Float, y: Float) = math.max(x, y)
  def min(x: Int, y: Int) = math.min(x, y)
  def min(x: Float, y: Float) = math.min(x, y)
  def rndF() = Rnd.nextFloat()
  def rndF(min: Float, max: Float) = min + (max - min) * Rnd.nextFloat
  def rndI() = Rnd.nextInt()
  def rndI(min: Int, max: Int) = min + Rnd.nextInt(max - min)
  def pow(x: Float, y: Float) = math.pow(x, y).toFloat
  def sin(x: Float) = math.sin(x).toFloat
  def sqrt(x: Float) = math.sqrt(x).toFloat
  def tan(x: Float) = math.tan(x).toFloat
  
  // use f.toRadians
  // def toDeg(rad: Float) = math.toDegrees(rad).toFloat
  // def toRad(deg: Float) = math.toRadians(deg).toFloat

  def length(x: Float, y: Float): Float =
    sqrt(lengthSquared(x, y))

  def length(x: Float, y: Float, z: Float): Float =
    sqrt(lengthSquared(x, y, z))

  def lengthSquared(x: Float, y: Float) =
    x * x + y * y

  def lengthSquared(x: Float, y: Float, z: Float) =
    x * x + y * y + z * z

  def distance(x1: Float, y1: Float, x2: Float, y2: Float): Float =
    sqrt(distanceSquared(x1, y1, x2, y2))

  def distance(x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float): Float =
    sqrt(distanceSquared(x1, y1, z1, x2, y2, z2))

  def distanceSquared(x1: Float, y1: Float, x2: Float, y2: Float) =
    val dx = x2 - x1
    val dy = y2 - y1
    dx * dx + dy * dy

  def distanceSquared(x1: Float, y1: Float, z1: Float, x2: Float, y2: Float, z2: Float) =
    val dx = x2 - x1
    val dy = y2 - y1
    val dz = z2 - z1
    dx * dx + dy * dy + dz * dz

  // 0 <= angle < 360

  def angle(x: Float, y: Float) =
    val abs = sqrt(x * x + y * y)
    val aco = acos(x / abs) //       0 <= acos <= pi
    val asi = asin(y / abs) // -pi / 2 <= asin <= pi / 2
    val ang = if y >= 0 then aco else 2 * Pi - aco
    ang // * 180 / Pi
  
  def ccw(p1: Vector2f, p2: Vector2f, p3: Vector2f): Boolean =
    ccw(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y)
  
  def ccw(p1: Vector2f, p2: Vector2f, x3: Float, y3: Float): Boolean =
    ccw(p1.x, p1.y, p2.x, p2.y, x3, y3)

  def ccw(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float): Boolean =
    (x1 * y2 - x2 * y1) - (x1 * y3 - x3 * y1) + (x2 * y3 - x3 * y2) < 0
  
