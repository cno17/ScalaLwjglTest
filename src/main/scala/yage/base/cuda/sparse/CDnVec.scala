package yage.base.cuda.sparse

import jcuda.jcusparse.JCusparse.cusparseCreateDnVec
import jcuda.jcusparse.JCusparse.cusparseDestroyDnVec
import jcuda.jcusparse.cusparseDnVecDescr
import yage.base.cuda.runtime.CudaObject

class CDnVec extends CudaObject[cusparseDnVecDescr]:

  override def create() =
    val id = cusparseDnVecDescr()
    cusparseCreateDnVec(id, 0, null, 0)
    id

  override def destroy() = cusparseDestroyDnVec(id.asConst)


