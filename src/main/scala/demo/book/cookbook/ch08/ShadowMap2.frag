#version 460 core

#define DEPTH_OFFSET 0.00002
#define LIGHT_INTENSITY 0.9
#define AMBIENT 0.1

in vec4 lightBiasedClipPosition;
in vec3 worldPosition;
in vec3 worldNormal;

uniform vec3 lightPosition;
uniform sampler2D depthTexture;

out vec4 fs_color;

void main(void) {
    // Convert the linearly interpolated clip-space position to NDC
    vec4 lightNDCPosition = lightBiasedClipPosition / lightBiasedClipPosition.w;
    // Sample the depth from the depth texture
    vec4 depth = texture2D(depthTexture, lightNDCPosition.xy);
    // Additionally, do standard lambertian/diffuse lighting
    float dot = max(0.0, dot(normalize(lightPosition - worldPosition), worldNormal));
    fs_color = vec4(AMBIENT, AMBIENT, AMBIENT, 1.0);
    // shadow test
    if (depth.z >= lightNDCPosition.z - DEPTH_OFFSET) {
        // lit
        fs_color += vec4(LIGHT_INTENSITY, LIGHT_INTENSITY, LIGHT_INTENSITY, 1.0) * dot;
    }
}
