package yage.base.cuda.sparse

import jcuda.jcusparse.cusparseDnVecDescr
import yage.base.cuda.driver.CuObject

/**
 *
 * @param n number of elements
 * @param nnz number of nonzero elements
 */

abstract class DnVec(val n: Int, val nnz: Int) extends CuObject[cusparseDnVecDescr]:

  override def create() =
    val res = cusparseDnVecDescr()
    // ...
    res
