#version 460 core

uniform vec3 color;

out vec4 fs_color;

void main() {
	fs_color = vec4(color, 1.0); // vec4(0.2, 0.8, 0.5, 1.0);
}