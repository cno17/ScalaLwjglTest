#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matMV;
uniform mat4 matVC;

uniform vec4 lightPosV;
uniform vec3 dInt;            // diffuse light intensity
uniform vec3 dRef;            // diffuse material reflectivity

out vec3 vs_color;

void main() {
    vec4 posV = matMV * posM;
    vec4 norV = normalize(matMV * norM);
    vec4 dirV = normalize(lightPosV - posV);
    vs_color = dInt * dRef * max(dot(dirV, norV), 0.0); // 0
    gl_Position = matVC * posV;
}
