#version 460 core

in vec3 position;
in vec3 normal;

uniform mat4 viewProjectionMatrix;
uniform mat4 lightViewProjectionMatrix;
uniform mat4 biasMatrix;

out vec4 lightBiasedClipPosition;
out vec3 worldPosition;
out vec3 worldNormal;

void main(void) {
    // Pass position and normal to the fragment shader
    // we do lighting computations in world coordinates)
    worldPosition = position;
    worldNormal = normal;
    // Compute vertex position as seen from the light and use linear
    // interpolation when passing it to the fragment shader
    lightBiasedClipPosition = biasMatrix * lightViewProjectionMatrix * vec4(position, 1.0);
    // Normally transform the vertex
    gl_Position = viewProjectionMatrix * vec4(position, 1.0);
}
