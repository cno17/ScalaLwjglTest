#version 460 core

// pos = posV!


struct PointLight {
    vec4 pos;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct Material {
    vec3 ambient;
    sampler2D diffuse;
    sampler2D specular;
    float shininess;
};

in vec4 posV;
in vec4 norV;
in vec2 tecV;

uniform PointLight light;
uniform Material material;

out vec4 fs_col;

// todo: phong -> blinn

void main() {
    vec3 colA = light.ambient * material.ambient;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(light.pos - posV);
    vec4 n = normalize(norV);
    float dotLN = max(0.0, dot(l, n));
    if (dotLN > 0) {
        vec4 v = vec4(normalize(-posV.xyz), 0.0);
        // todo nice vec4 v = normalize(vec4(0.0) - posV);
        vec4 r = reflect(-l, n);
        float dotVR = max(0.0, dot(v, r));
        colD = light.diffuse * texture(material.diffuse, tecV).rgb * dotLN;
        colS = light.specular * texture(material.specular, tecV).rgb * pow(dotVR, material.shininess);
    }    
    vec3 col = colA + colD + colS;
    fs_col = vec4(col, 1.0);
    // fs_col = vec4(colA + colD + colS, 1.0);
} 