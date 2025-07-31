#version 460 core

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matMV;
uniform mat4 matVC;

uniform vec4 lightPosV;

// light intensity, material reflectivity

uniform vec3 aInt;
uniform vec3 dInt;
uniform vec3 sInt;
uniform vec3 aRef;
uniform vec3 dRef;
uniform vec3 sRef;

uniform float shininess;

out vec3 vs_color;

void main() {
    vec4 p = matMV * posM;
    vec4 n = normalize(matMV * norM);
    vec4 l = normalize(lightPosV - p);
    vec4 v = normalize(-lightPosV);
    vec4 r = reflect(-l, n);
    float dotLN = max(dot(l, n), 0.0);
    float dotRV = max(dot(r, v), 0.0);
    vec3 aCol = aInt * aRef;
    vec3 dCol = dInt * dRef * dotLN;
    vec3 sCol = sInt * sRef * pow(dotRV, shininess);
    vs_color = aCol + dCol + sCol;
    gl_Position = matVC * p;
}
