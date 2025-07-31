#version 460 core

in vec2 vs_tec;

layout (binding = 0) uniform Blob {
    vec4 innerColor;
    vec4 outerColor;
    float innerRadius;
    float outerRadius;
} blob;

out vec4 fs_col;

void main() {
    float x = 2.0 * (vs_tec.x - 0.5);
    float y = 2.0 * (vs_tec.y - 0.5);
    float abs = sqrt(x * x + y * y);
    float fac = smoothstep(blob.innerRadius, blob.outerRadius, abs);
    fs_col = mix(blob.innerColor, blob.outerColor, fac);
};
