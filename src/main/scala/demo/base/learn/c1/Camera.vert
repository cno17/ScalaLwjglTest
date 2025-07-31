#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 4) in vec2 tecM;

uniform mat4 matMC;

out vec2 vs_tec;

void main() {
	gl_Position = matMC * posM;
	vs_tec = tecM;
}