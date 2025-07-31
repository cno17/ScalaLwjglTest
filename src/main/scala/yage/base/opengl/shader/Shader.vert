#version 460 core

layout (location = 0) in vec4 pos;
layout (location = 1) in vec4 nor;
layout (location = 4) in vec2 tec;

void main() {
    gl_Position = pos;
};

// #include "Light/PhongBlinn.glsl"
#include "Math/Math.glsl"
