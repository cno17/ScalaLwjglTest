#version 460 core

in vec2 vs_tec;

uniform float time;

out vec4 fs_col;

void main() {
    float r = vs_tec.x * 0.5 * (1 + sin(2 * time));
    float g = vs_tec.y * 0.5 * (1 + cos(3 * time));
    float b = vs_tec.x * vs_tec.y * (1 + cos(5 * time));
    fs_col = vec4(r, g, b, 1);
};
