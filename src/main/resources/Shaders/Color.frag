#version 460 core

struct ColorMaterial {
    vec4 color;
};

uniform ColorMaterial material;

out vec4 fs_col;

void main() {
    fs_col = material.color;
}
