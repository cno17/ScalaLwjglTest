#version 460 core

struct Light {
    vec4 posV;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;
layout (location = 2) in vec4 tanM;
layout (location = 4) in vec2 tecM;

out vec4 lightDirT;
out vec4 viewDirT;
out vec2 texCoord;

uniform Light light;
uniform mat4 matMV;
uniform mat4 matVC;

vec4 cross(vec4 v, vec4 w) {
    return vec4(cross(vec3(v), vec3(w)), 0.0);
}

void main() {
    // Transform position, normal and tangent to view space
    // and compute the bitangent
    vec4 posV = matMV * posM;
    vec3 norV = normalize(matMV * norM);
    vec3 tanV = normalize(matMV * tanM);
    vec3 bitV = normalize(cross(norV, tanV)); // normalize necessary?

    // Matrix for transformation to tangent space
    // from view space?
    // constructible from vectors?
    mat4 matVT = mat4(
        tanV.x, bitV.x, norV.x, 0.0,
        tanV.y, bitV.y, norV.y, 0.0,
        tanV.z, bitV.z, norV.z, 0.0,
        0.0, 0.0, 0.0, 1.0
    );

    // Transform light direction and view direction to tangent space
    lightDirT = matVT * (light.posV - posV);
    viewDirT = matVT * normalize(-posV);
    texCoord = tecM;
    gl_Position = matVC * posV;
}
