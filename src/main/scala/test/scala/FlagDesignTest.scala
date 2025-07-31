package test.base.scala

import test.base.scala.FlagDesignTest.StorageFlags.ReadOnly
import test.base.scala.FlagDesignTest.StorageFlags.WriteOnly

object FlagDesignTest:


  trait Identifiable:
    def id: Int

  enum StorageFlags(val id: Int) extends Identifiable:
    case ReadOnly extends StorageFlags(1)
    case WriteOnly extends StorageFlags(2)

  class Flags[E <: Identifiable]:
    var value = 0
    def clear() = value = 0
    def set(e: E) = value |= e.id
    def clear(e: E) = value |= e.id // ???
    def flip(e: E) = value |= e.id // ???


  def main(args: Array[String]) =
    val flags = Flags[StorageFlags]
    flags.set(ReadOnly)
    flags.set(WriteOnly)
    println(flags.value)
