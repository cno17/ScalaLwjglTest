#version 460 core

struct SpotLight {
    vec4 posV;
    vec4 dirV;
    float maxAng;
    float angAtt;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float shininess;
};

in vec4 posV;
in vec4 norV;

uniform SpotLight[2] lights;
uniform Material material;

out vec4 col;

// todo: linear attenuation

vec3 phongBlinn(int i) {
    vec3 colA = lights[i].ambient * material.ambient;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(lights[i].posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(dot(l, n), 0.0);
    if (dotLN > 0) {
        float cos = dot(-l, lights[i].dirV);
        float ang = acos(cos); // degrees
        if (ang < lights[i].maxAng) {
            vec4 v = normalize(vec4(-posV.xyz, 0.0));
            vec4 h = normalize(l + v);
            float dotHN = max(dot(h, n), 0.0);
            float spotScale = pow(cos, lights[i].angAtt);
            colD = spotScale * lights[i].diffuse * material.diffuse * dotLN;
            colS = spotScale * lights[i].specular * material.specular * pow(dotHN, material.shininess);
        }
    }
    return colA + colD + colS;
}

void main() {
    vec3 col0 = phongBlinn(0);
    vec3 col1 = phongBlinn(1);
    col = vec4(col0 + col1, 1.0);
}



