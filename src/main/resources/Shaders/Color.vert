#version 460 core

layout (location = 0) in vec4 posM;

uniform mat4 matMC;

void main() {
    gl_Position = matMC * posM;
}