#version 460 core

in vec3 vs_color;

layout (location = 0) out vec4 fs_color;

void main() {
    // fs_color = vec4(0.0, 1.0, 0.0, 1.0);
    fs_color = vec4(vs_color, 1.0);
}
