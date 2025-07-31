#version 460 core

struct Material {
    vec4 diffuse;
    vec4 specular;
    float shininess;
};

struct SpotLight {
    vec4 ambient;
    vec4 diffuse;
    vec4 specular;
    vec4 posV;
    vec4 dirV;
    float maxAng;
    float angAtt;
    // float constant;
    // float linear;
    // float quadratic;
};

in vec4 posV;
in vec4 norV;


layout (std430, binding = 0) buffer Block {
    Material material;
    SpotLight light;
};

out vec4 fs_col;


// phong-blinn-spot
// todo: linear attenuation

void main() {
    vec4 colA = material.diffuse * light.ambient;
    vec4 colD = vec4(0.0);
    vec4 colS = vec4(0.0);
    vec4 l = normalize(light.posV - posV);
    vec4 n = normalize(norV);
    float dotLN = max(0.0, dot(l, n));
    if (dotLN > 0) {
        float cos = dot(-l, light.dirV);
        float ang = acos(cos);
        if (ang < light.maxAng) {
            vec4 v = normalize(vec4(0.0) - posV);
            vec4 h = normalize(l + v);
            float dotHN = max(0.0, dot(h, n));
            float sca = pow(cos, light.angAtt);
            colD = sca * material.diffuse * light.diffuse * dotLN;
            colS = sca * material.specular * light.specular * pow(dotHN, material.shininess);
        }
    }
    fs_col = colA + colD + colS;
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