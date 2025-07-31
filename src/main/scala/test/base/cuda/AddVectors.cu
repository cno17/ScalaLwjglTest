
extern "C" __global__ void add(int n, float *a, float *b, float *res) {
    int i = blockIdx.x * blockDim.x + threadIdx.x;
    if (i < n) {
        res[i] = a[i] + b[i];
    }
}
