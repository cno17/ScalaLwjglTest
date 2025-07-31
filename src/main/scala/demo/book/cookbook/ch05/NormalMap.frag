#version 460 core

struct Light {
  vec4 posV;
  vec3 ambient;
  vec3 diffuse;
  vec3 specular;
};

struct Material {
  vec3 specular;
  float shininess;
};

in vec4 lightDirT;
in vec4 viewDirT;
in vec2 texCoord;

layout(binding = 0) uniform sampler2D diffuseMap;
layout(binding = 1) uniform sampler2D normalMap;

uniform Light light;
uniform Material material;

layout(location = 0) out vec4 FragColor;

vec3 blinnPhong(vec4 n) {
  vec3 texColor = texture(diffuseMap, texCoord).rgb;
  vec3 ambient = light.ambient * texColor;
  vec3 s = normalize(lightDirT);
  float sDotN = max(dot(s, n), 0.0);
  vec3 diffuse = texColor * sDotN;
  vec3 spec = vec3(0.0);
  if (sDotN > 0.0) {
    vec3 v = normalize(ViewDirT);
    vec3 h = normalize(v + s);
    spec = material.specular * pow(max(dot(h, n), 0.0), material.shininess);
  }
  return ambient + Light.L * (diffuse + spec);
}

void main() {
  // Lookup the normal from the normal map
  // vec3 normal = 2.0 * texture( NormalMapTex, TexCoord ).xyz - 1.0;
  //  FragColor = vec4( blinnPhong(normal), 1.0 );

  vec3 norm = texture(normalMap, TexCoord).xyz;
  norm.xy = 2.0 * norm.xy - 1.0;
  FragColor = vec4(blinnPhong(norm), 1.0);
}
