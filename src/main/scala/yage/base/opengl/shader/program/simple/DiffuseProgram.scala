package yage.base.opengl.shader.program.simple

import yage.base.opengl.shader.Shader
import yage.base.opengl.shader.program.Program

class DiffuseProgram extends Program:
  
  create()
  attach(Shader(resFile("Shaders/Light/Simple/Diffuse.vert")))
  attach(Shader(resFile("Shaders/Light/Simple/Diffuse.frag")))
  if !link() then throw Exception(infoLog)


