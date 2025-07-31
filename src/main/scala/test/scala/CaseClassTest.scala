package test.scala

import org.lwjgl.BufferUtils

object CaseClassTest:

  case class Person(name: String):
    
    def this() = this("Bob")
  
  def main(args: Array[String]) =
    val a = Person("Anna")
    val b = new Person()
    println(2)
