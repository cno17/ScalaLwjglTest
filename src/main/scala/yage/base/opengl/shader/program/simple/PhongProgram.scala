package yage.base.opengl.shader.program.simple

import yage.base.opengl.shader.Shader
import yage.base.opengl.shader.program.Program

class PhongProgram extends Program:
  
  create()
  attach(Shader(resFile("Shaders/Light/Simple/Phong.vert")))
  attach(Shader(resFile("Shaders/Light/Simple/Phong.frag")))
  if !link() then throw Exception(infoLog)


