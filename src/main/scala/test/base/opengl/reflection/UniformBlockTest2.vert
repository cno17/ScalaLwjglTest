#version 460 core

struct PointLight {
    vec3 pos;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

layout (binding = 1) uniform LightBlock {
    PointLight lights[2];
} lightBlock;

void main() {
    PointLight l0 = lightBlock.lights[0];
    PointLight l1 = lightBlock.lights[1];
    vec3 v1 = l0.pos + l0.ambient;
    vec3 v2 = l1.pos;
    gl_Position = vec4(v1 + v2, 1.0);
}
