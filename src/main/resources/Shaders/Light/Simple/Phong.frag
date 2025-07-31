#version 460 core

struct Material {
    vec4 diffuse;
    vec4 specular;
    float shininess;
};

struct PointLight {
    vec4 ambient;
    vec4 diffuse;
    vec4 specular;
    vec4 posV;
};

in vec4 posV;
in vec4 norV;

uniform Material material;
uniform PointLight light;

out vec4 fs_col;

void main() {
    vec4 colA = material.diffuse * light.ambient;
    vec4 colD = vec4(0.0);
    vec4 colS = vec4(0.0);
    vec4 l = normalize(light.posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(0.0, dot(l, n));
    if (dotLN > 0) {
        vec4 v = normalize(vec4(0.0) - posV);
        vec4 r = reflect(-l, n);
        float dotVR = max(0.0, dot(v, r));
        colD = material.diffuse * light.diffuse * dotLN;
        colS = material.specular * light.specular * pow(dotVR, material.shininess);
    }    
    fs_col = colA + colD + colS;
} 