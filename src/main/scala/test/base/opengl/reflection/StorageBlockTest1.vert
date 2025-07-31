#version 460 core

struct PointLight {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    vec3 posV;
};

layout (binding = 1) buffer LightBlock {
    PointLight lights[];
} lightBlock;

void main() {
    int s = lightBlock.lights.length();
    PointLight l0 = lightBlock.lights[0];
    PointLight l1 = lightBlock.lights[1];
    vec3 v1 = l0.posV + l0.ambient;
    vec3 v2 = l1.posV;
    gl_Position = vec4(v1 + v2, 1.0);
}
