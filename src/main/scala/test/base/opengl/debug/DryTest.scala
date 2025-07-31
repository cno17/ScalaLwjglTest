package test.base.opengl.debug

import yage.base.opengl.debug.DebugMessage.Severity

object DryTest:

  trait IdHolder:
    def id: Int

  trait IdFinder[H <: IdHolder]:
    def values: Array[H]
    def apply(id: Int) = values.find(_.id == id).get


  object Day extends IdFinder[Day]

  enum Day(val id: Int) extends IdHolder:

    case Mon extends Day(1)
    case Tue extends Day(2)
    case Wen extends Day(3)

  def main(args: Array[String]) =
    val d = Day(5)
    println(d)
