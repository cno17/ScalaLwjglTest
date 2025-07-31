package test.base.cuda.runtime

import jcuda.jcublas.JCublas
import jcuda.runtime.JCuda
import jcuda.runtime.JCuda.cudaGetDeviceCount
import jcuda.runtime.JCuda.cudaGetDeviceProperties
import jcuda.runtime.cudaDeviceProp


object UnifiedMemory:

  def main(args: Array[String]) =
    JCuda.setExceptionsEnabled(true);
    JCublas.setExceptionsEnabled(true);
    println("ok")

/*
// Check if the device supports managed memory
int supported
[]
= {0};
cudaDeviceGetAttribute(supported, cudaDevAttrManagedMemory, 0);
if (supported[0] == 0) {
  System.err.println("Device does not support managed memory");
  return;
}

// Allocate managed memory that is accessible to the host
int n = 10;
long size = n * Sizeof.FLOAT;
Pointer p = new Pointer();
cudaMallocManaged(p, size, cudaMemAttachHost);

// Obtain the byte buffer from the pointer. This is supported only
// for memory that was allocated to be accessible on the host:
ByteBuffer bb = p.getByteBuffer(0, size);

System.out.println("Buffer on host side: " + bb);

// Fill the buffer with sample data
FloatBuffer fb = bb.order(ByteOrder.nativeOrder()).asFloatBuffer();
for (int i
= 0;
i < n;
i ++
)
{
  fb.put(i, i);
}

// Make the buffer accessible to all devices
cudaStreamAttachMemAsync(null, p, 0, cudaMemAttachGlobal);
cudaStreamSynchronize(null);

// Use the pointer in a device operation (here, a dot product with
// JCublas, for example). The data that was filled in by the host
// will now be used by the device.
cublasHandle handle = new cublasHandle();
cublasCreate(handle);
float result
[]
= {-1.0f};
cublasSdot(handle, n, p, 1, p, 1, Pointer.to(result));
System.out.println("Result: " + result[0]);
}
}
*/