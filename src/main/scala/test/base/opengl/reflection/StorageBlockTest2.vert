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
    vec3 pos = vec3(0.0);
    for (int i = 0; i < lightBlock.lights.length(); i++) {
        pos += lightBlock.lights[i].posV;
    }
    gl_Position = vec4(pos, 1.0);
}
