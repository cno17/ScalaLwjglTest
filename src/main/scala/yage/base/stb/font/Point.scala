package yage.base.stb.font

case class Point(var x: Int, var y: Int):

  def set(x: Int, y: Int) =
    this.x = x
    this.y = y
    this

