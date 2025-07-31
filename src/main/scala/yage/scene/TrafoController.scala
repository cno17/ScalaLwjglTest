package yage.scene

import org.joml.Matrix4f
import yage.base.glfw.GlApp
import yage.base.glfw.input.Key
import yage.base.glfw.input.Mouse.Button

// rename: TrafoManipulator

trait TrafoController(var app: App, var mat: Matrix4f):

  enum Trafo:
    case S, R, T

  enum Axis:
    case X, Y, Z

  enum Order:
    case L, R

  case class Mode(var trafo: Trafo, var axis: Axis, var order: Order)

  var mode = Mode(Trafo.R, Axis.X, Order.L)

  var sca = 0f
  var rot = 0f
  var tra = 0f

  register()

  def register(): Unit
