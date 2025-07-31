package yage.scene

import org.joml.Matrix4f
import yage.base.glfw.GlApp
import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.input.Mouse.Button
import yage.base.glfw.window.Window


class TrafoKeyController(app: App, mat: Matrix4f) extends TrafoController(app, mat):

  override def register() =
    Keyboard.keyPressedListeners += keyPressed
    Keyboard.keyRepeatedListeners += keyRepeated

  def keyPressed(k: Key) =
    if k == Key.S then mode.trafo = Trafo.S
    if k == Key.R then mode.trafo = Trafo.R
    if k == Key.T then mode.trafo = Trafo.T
    if k == Key.X then mode.axis = Axis.X
    if k == Key.Y then mode.axis = Axis.Y
    if k == Key.Z then mode.axis = Axis.Z
    if k == Key.F1 then mode.order = Order.L
    if k == Key.F2 then mode.order = Order.R
    Window.setTitle(s"$mode")
    if k == Key.U then mat.identity()
    if k == Key.Left then
      sca = +0.90f
      rot = -0.05f
      tra = -1.00f
      exec()
    if k == Key.Right then
      sca = +1.10f
      rot = +0.05f
      tra = +1.00f
      exec()

  def keyRepeated(k: Key) =
    if k == Key.Left then
      sca = +0.90f
      rot = -0.05f
      tra = -1.00f
      exec()
    if k == Key.Right then
      sca = +1.10f
      rot = +0.05f
      tra = +1.00f
      exec()

  def exec() =
    mode match
      case Mode(Trafo.S, _, Order.L) => mat.scaleLocal(sca)
      case Mode(Trafo.S, _, Order.R) => mat.scale(sca)
      // TODO case Mode(Trafo.R, Axis.X, Order.L) => mat.rotateL(1, 0, 0, rot)
      // TODO case Mode(Trafo.R, Axis.Y, Order.L) => mat.rotateL(0, 1, 0, rot)
      // TODO case Mode(Trafo.R, Axis.Z, Order.L) => mat.rotateL(0, 0, 1, rot)
      // TODO case Mode(Trafo.R, Axis.X, Order.R) => mat.rotateR(1, 0, 0, rot)
      // TODO case Mode(Trafo.R, Axis.Y, Order.R) => mat.rotateR(0, 1, 0, rot)
      // TODO case Mode(Trafo.R, Axis.Z, Order.R) => mat.rotateR(0, 0, 1, rot)
      case Mode(Trafo.T, Axis.X, Order.L) => mat.translateLocal(tra, 0, 0)
      case Mode(Trafo.T, Axis.Y, Order.L) => mat.translateLocal(0, tra, 0)
      case Mode(Trafo.T, Axis.Z, Order.L) => mat.translateLocal(0, 0, tra)
      case Mode(Trafo.T, Axis.X, Order.R) => mat.translate(tra, 0, 0)
      case Mode(Trafo.T, Axis.Y, Order.R) => mat.translate(0, tra, 0)
      case Mode(Trafo.T, Axis.Z, Order.R) => mat.translate(0, 0, tra)
    