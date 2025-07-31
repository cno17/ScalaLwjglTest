#version 460 core

// phong blinn

struct Color {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct Attenuation {
    float constant;
    float linear;
    float quadratic;
};

struct PointLight {
    vec4 posV;
    Color color;
    Attenuation attenuation;
};

struct StarLight {
    vec4 dirV;
    Color color;
};

struct SpotLight {
    vec4 posV;
    vec4 dirV;
    Color color;
    float maxAng;
    float angAtt;
};

struct Material {
    Color color;
    float shininess;
};

in vec4 posV;
in vec4 norV;
in vec2 tecV;

layout (binding = 0, std430) buffer Point {
    PointLight[] lights;
} point;

layout (binding = 1, std430) buffer Star {
    StarLight[] lights;
} star;

layout (binding = 2, std430) buffer Spot {
    SpotLight[] lights;
} spot;

uniform Material material;

out vec4 col;

/*
vec3 pointLightColor(int i) {
    PointLight light = point.lights[i];
    vec3 colA = light.ambientColor * material.ambientColor;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(light.posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(dot(l, n), 0.0);
    if (dotLN > 0) {
        vec4 v = vec4(normalize(-posV.xyz), 0.0);
        // todo h
        vec4 r = reflect(-l, n);
        float dotVR = max(0.0, dot(v, r));
        colD = light.diffuseColor * material.diffuseColor * dotLN;
        colS = light.specularColor * material.specularColor * pow(dotVR, material.shininess);
    }    
    return colA + colD + colS;
}
*/

vec3 spotLightColor(int i) {
    SpotLight light = spot.lights[i];
    vec3 colA = light.color.ambient * material.color.ambient;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(light.posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(dot(l, n), 0.0);
    if (dotLN > 0) {
        float cos = dot(-l, light.dirV);
        float ang = acos(cos); // degrees
        if (ang < light.maxAng) {
            vec4 v = normalize(vec4(-posV.xyz, 0.0));
            vec4 h = normalize(l + v);
            float dotHN = max(dot(h, n), 0.0);
            float scale = pow(cos, light.angAtt);
            colD = scale * light.color.diffuse * material.color.diffuse * dotLN;
            colS = scale * light.color.specular * material.color.specular * pow(dotHN, material.shininess);
        }
    }
    return colA + colD + colS;
}

void main() {
    vec3 color = vec3(0.0);
    for (int i = 0; i < spot.lights.length(); i++) {
        color += spotLightColor(i);
    }
    // saturate!
    col = vec4(color, 1.0);
}
