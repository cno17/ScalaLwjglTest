

// pos = posV!

struct ADS {
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct PointLight {
    vec4 pos;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct StarLight {
    vec4 pos;
    vec3 ambient;
    vec3 diffuse;
    vec3 specular;
};

struct SpotLight {
    vec4 pos;
    vec4 dir;
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
