#version 460 core

layout (location = 0) in vec4 pos;
layout (location = 4) in vec2 tec;

out vec2 vs_tec;

void main() {
    vs_tec = tec;
    gl_Position = pos;
}
