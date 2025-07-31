package test.base

import yage.base.Logic.*

object LogicTest:

  def main(args: Array[String]) =
    val a = not(false)
    for i <- 0 to 10 do
      if not(i % 2 == 0) and i > 5 then println(i)
