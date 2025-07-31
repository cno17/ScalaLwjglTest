#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;
layout (location = 4) in vec2 tecM;

uniform mat4 matMV;
uniform mat4 matVC;

out vec4 posV;
out vec4 norV;
out vec2 tecV;

void main() {
    posV = matMV * posM;
    norV = matMV * norM;
    tecV = tecM;
    gl_Position = matVC * posV;
}