package yage.base.cuda.driver

import jcuda.driver.CUfunction_attribute.*

enum CuFunctionAttribute(val id: Int):
  case BinaryVersion extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_BINARY_VERSION)
  case CacheMode extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_CACHE_MODE_CA)
  case ClusterSchedulingPolicyReference extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_CLUSTER_SCHEDULING_POLICY_PREFERENCE)
  case ClusterSizeMustBeSet extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_CLUSTER_SIZE_MUST_BE_SET)
  case ConstSizeBytes extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_CONST_SIZE_BYTES)
  case LocalSizeBytes extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_LOCAL_SIZE_BYTES)
  case MaxDynamicSharedSizeBytes extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_MAX_DYNAMIC_SHARED_SIZE_BYTES)
  case MaxThreadsPerBlock extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_MAX_THREADS_PER_BLOCK)
  case NonPortableClusterSizeAllowed extends CuFunctionAttribute(CU_FUNC_ATTRIBUTE_NON_PORTABLE_CLUSTER_SIZE_ALLOWED)
// ...