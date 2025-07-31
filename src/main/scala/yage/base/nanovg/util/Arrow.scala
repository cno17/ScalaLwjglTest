package yage.base.nanovg.util

import org.joml.Vector2f
import yage.base.FMath.sqrt
import yage.shape.Trigon2

object Arrow:

  def n(x: Float, y: Float, l: Float) =
    val o = l / sqrt(2)
    Trigon2(Vector2f(x, y), Vector2f(x - o, y + o), Vector2f(x + o, y + o))

  def w(x: Float, y: Float, l: Float) =
    val o = l / sqrt(2)
    Trigon2(Vector2f(x, y), Vector2f(x + o, y + o), Vector2f(x + o, y - o))

  def s(x: Float, y: Float, l: Float) =
    val o = l / sqrt(2)
    Trigon2(Vector2f(x, y), Vector2f(x + o, y - o), Vector2f(x - o, y - o))

  def e(x: Float, y: Float, l: Float) =
    val o = l / sqrt(2)
    Trigon2(Vector2f(x, y), Vector2f(x - o, y - o), Vector2f(x - o, y + o))

  def nw(x: Float, y: Float, l: Float) =
    Trigon2(Vector2f(x, y), Vector2f(x, y + l), Vector2f(x + l, y))

  def sw(x: Float, y: Float, l: Float) =
    Trigon2(Vector2f(x, y), Vector2f(x + l, y), Vector2f(x, y - l))

  def se(x: Float, y: Float, l: Float) =
    Trigon2(Vector2f(x, y), Vector2f(x, y - l), Vector2f(x - l, y))

  def ne(x: Float, y: Float, l: Float) =
    Trigon2(Vector2f(x, y), Vector2f(x - l, y), Vector2f(x, y + l))

