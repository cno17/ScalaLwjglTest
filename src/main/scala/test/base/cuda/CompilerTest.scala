package test.cuda

import yage.base.cuda.driver.CuApp
import yage.base.cuda.driver.CuProgram

object CompilerTest extends CuApp:

  override def execute() =
    val prog = CuProgram(srcFile("AddVectors.cu").chars)
    prog.compile()
    val ptx = prog.getPtx()
    prog.destroy()
    println(ptx)
