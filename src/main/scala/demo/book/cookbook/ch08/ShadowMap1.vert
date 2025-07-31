#version 460 core

in vec3 position;

uniform mat4 viewProjectionMatrix;

void main(void) {
    gl_Position = viewProjectionMatrix * vec4(position, 1.0);
}
