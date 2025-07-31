#version 460 core

struct SpotLight {
    vec4 pos;
    vec4 dir;
    float maxAng;
    float angAtt;
    // float constant;
    // float linear;
    // float quadratic;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct Material {
    sampler2D diffuse;
    sampler2D specular;
    float shininess;
};

in vec4 posV;
in vec4 norV;
in vec2 tecV;

// uniform vec3 viewPos;

uniform SpotLight light;
uniform Material material;

out vec4 fs_col;


// blinn-softspot
// todo: linear attenuation

void main() {
    vec3 matD = texture(material.diffuse, tecV).rgb;
    vec3 matS = texture(material.specular, tecV).rgb;
    vec3 colA = light.ambient * matD;
    vec3 colD = vec3(0.0);
    vec3 colS = vec3(0.0);
    vec4 l = normalize(light.pos - posV);
    vec4 n = normalize(norV);
    float dotLN = max(0.0, dot(l, n));
    if (dotLN > 0) {
        float cos = dot(-l, light.dir);
        float ang = acos(cos); // degrees
        if (ang < light.maxAng) {
            vec4 v = normalize(vec4(-posV.xyz, 0.0));
            vec4 h = normalize(l + v);
            float dotHN = max(0.0, dot(h, n));
            float spotScale = pow(cos, light.angAtt);
            colD = spotScale * light.diffuse * matD * dotLN;
            colS = spotScale * light.specular * matS * pow(dotHN, material.shininess);
        }
    }
    fs_col = vec4(colA + colD + colS, 1.0);
}





/*

void main() {
    vec3 ambient = light.ambient * material.ambient;
    
    // diffuse 
    vec3 norm = normalize(Normal);
    vec3 lightDir = normalize(light.position - FragPos);
    float diff = max(dot(norm, lightDir), 0.0);
    vec3 diffuse = light.diffuse * diff * texture(material.diffuse, TexCoords).rgb;  
    
    // specular
    vec3 viewDir = normalize(viewPos - FragPos);
    vec3 reflectDir = reflect(-lightDir, norm);  
    float spec = pow(max(dot(viewDir, reflectDir), 0.0), material.shininess);
    vec3 specular = light.specular * spec * texture(material.specular, TexCoords).rgb;  
    
    // spotlight (soft edges)
    float theta = dot(lightDir, normalize(-light.direction)); 
    float epsilon = (light.cutOff - light.outerCutOff);
    float intensity = clamp((theta - light.outerCutOff) / epsilon, 0.0, 1.0);
    diffuse  *= intensity;
    specular *= intensity;
    
    // attenuation
    float distance    = length(light.position - FragPos);
    float attenuation = 1.0 / (light.constant + light.linear * distance + light.quadratic * (distance * distance));    
    ambient  *= attenuation; 
    diffuse   *= attenuation;
    specular *= attenuation;   
        
    vec3 result = ambient + diffuse + specular;
    FragColor = vec4(result, 1.0);
} 


*/