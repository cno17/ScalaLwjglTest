package yage.base.opengl

import yage.base.FileInfo
import yage.base.StackUser

trait Object extends FileInfo, StackUser:

  val id = create()

  def create(): Int
  def destroy(): Unit
