package test.scala

object AccessorTest:

  private class Camera:

    private var _num = 1f
    private var _inv = 1f / _num

    def num = _num
    def num_=(v: Float) = {_num = v; _inv = 1f / _num}
    def inv = _inv

    override def toString = s"(num = $num, inv = $inv)"

  def main(args: Array[String]) =
    val c = Camera()
    c.num = 5
    println(c)
