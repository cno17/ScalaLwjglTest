#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matMV;
uniform mat4 matVC;

out vec4 posV;
out vec4 norV;

void main() {
    posV = matMV * posM;
    norV = matMV * norM;
    gl_Position = matVC * posV;
}