#version 460 core

struct ADS {
    vec3 a;
    vec3 d;
    vec3 s;
};

struct CLQ {
    float c;
    float l;
    float q;
};

struct PointLight {
    vec4 posV;
    ADS intensity;
    // CLQ attenuation;
};

struct Material {
    ADS reflectivity;
    float shininess;
};

layout (location = 0) in vec4 posM;
layout (location = 1) in vec4 norM;

uniform mat4 matMV;
uniform mat4 matVC;
uniform PointLight light;
uniform Material material;

out vec3 vs_color;

void main() {
    vec4 p = matMV * posM;
    vec4 n = normalize(matMV * norM);
    vec4 l = normalize(light.posV - p);
    vec4 v = normalize(-p);
    vec4 r = reflect(-l, n);
    float dotLN = max(dot(l, n), 0.0);
    float dotRV = max(dot(r, v), 0.0);
    vec3 aCol = light.intensity.a * material.reflectivity.a;
    vec3 dCol = light.intensity.d * material.reflectivity.d * dotLN;
    vec3 sCol = light.intensity.s * material.reflectivity.s * pow(dotRV, material.shininess);
    vs_color = aCol + dCol + sCol;
    gl_Position = matVC * p;
}
