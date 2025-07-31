package yage.base.cuda.sparse

import jcuda.jcusparse.cusparseIndexType.*

enum IndexType(val id: Int):

  case U16 extends IndexType(CUSPARSE_INDEX_16U)
  case I32 extends IndexType(CUSPARSE_INDEX_32I)
  case I64 extends IndexType(CUSPARSE_INDEX_64I)