#version 460 core

struct SpotLight {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    vec4 posV;
    vec4 dirV;
    float maxAng;
    float angAtt;
};

struct Material {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
    float shininess;
};

in vec4 posV;
in vec4 norV;

uniform Block {
    Material material;
    SpotLight[2] lights;
} block;
    

out vec4 col;

// todo: linear attenuation

vec3 phongBlinn(int i) {
    vec3 colA = block.lights[i].ambient * block.material.ambient;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(block.lights[i].posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(dot(l, n), 0.0);
    if (dotLN > 0) {
        float cos = dot(-l, block.lights[i].dirV);
        float ang = acos(cos); // degrees
        if (ang < block.lights[i].maxAng) {
            vec4 v = normalize(vec4(-posV.xyz, 0.0));
            vec4 h = normalize(l + v);
            float dotHN = max(dot(h, n), 0.0);
            float spotScale = pow(cos, block.lights[i].angAtt);
            colD = spotScale * block.lights[i].diffuse * block.material.diffuse * dotLN;
            colS = spotScale * block.lights[i].specular * block.material.specular * pow(dotHN, block.material.shininess);
        }
    }
    return colA + colD + colS;
}

void main() {
    vec3 col0 = phongBlinn(0);
    vec3 col1 = phongBlinn(1);
    col = vec4(col0 + col1, 1.0);
}



