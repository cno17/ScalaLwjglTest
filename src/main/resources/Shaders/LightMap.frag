#version 460 core

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
    // Color color;
    vec3 ambientColor;
    vec3 diffuseColor;
    vec3 specularColor;
};

struct StarLight {
    vec4 dirV;
    vec3 ambientColor;
    vec3 diffuseColor;
    vec3 specularColor;
};

struct SpotLight {
    vec4 posV;
    vec4 dirV;
    vec3 ambientColor;
    vec3 diffuseColor;
    vec3 specularColor;
    float maxAng;
    float angAtt;
    // float constant;
    // float linear;
    // float quadratic;
};

struct Material {
    vec3 ambientColor;
    vec3 diffuseColor;
    vec3 specularColor;
    float shininess;
};

in vec4 posV;
in vec4 norV;
in vec2 tecV;

uniform PointLight pointLight;
uniform SpotLight spotLight;
uniform Material material;

out vec4 fs_col;

vec3 pointLightColor() {
    vec3 colA = pointLight.ambientColor * material.ambientColor;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(pointLight.posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(0.0, dot(l, n));
    if (dotLN > 0) {
        vec4 v = vec4(normalize(-posV.xyz), 0.0);
        vec4 r = reflect(-l, n);
        float dotVR = max(0.0, dot(v, r));
        colD = pointLight.diffuseColor * texture(material.diffuseMap, tecV).rgb * dotLN;
        colS = pointLight.specularColor * texture(material.specularMap, tecV).rgb * pow(dotVR, material.shininess);
    }    
    return colA + colD + colS;
}

vec3 spotLightColor() {
    vec3 matD = texture(material.diffuseMap, tecV).rgb;
    vec3 matS = texture(material.specularMap, tecV).rgb;
    vec3 colA = spotLight.ambientColor * matD;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(spotLight.posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(0.0, dot(l, n));
    if (dotLN > 0) {
        float cos = dot(-l, spotLight.dirV);
        float ang = acos(cos); // degrees
        if (ang < spotLight.maxAng) {
            vec4 v = normalize(vec4(-posV.xyz, 0.0));
            vec4 h = normalize(l + v);
            float dotHN = max(0.0, dot(h, n));
            float spotScale = pow(cos, spotLight.angAtt);
            colD = spotScale * spotLight.diffuseColor * matD * dotLN;
            colS = spotScale * spotLight.specularColor * matS * pow(dotHN, material.shininess);
        }
    }
    return colA + colD + colS;
}

// todo: phong -> blinn

void main() {
    vec3 col1 = 0.9 * pointLightColor();
    vec3 col2 = 0.0 * spotLightColor();
    fs_col = vec4(col1 + col2, 1.0);
}
