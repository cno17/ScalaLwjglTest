package yage.base.assimp

import yage.base.Flag
import yage.base.Flags

import org.lwjgl.assimp.Assimp.*

enum PostProcessingFlag(val id: Int) extends Flag:
  case CalcTangentSpace extends PostProcessingFlag(aiProcess_CalcTangentSpace)
  case GenBoundingBoxes extends PostProcessingFlag(aiProcess_GenBoundingBoxes)
  case GenNormals extends PostProcessingFlag(aiProcess_GenNormals)
  case GenSmoothNormals extends PostProcessingFlag(aiProcess_GenSmoothNormals)
  case GenUVCoords extends PostProcessingFlag(aiProcess_GenUVCoords)
  case JoinIdenticalVertices extends PostProcessingFlag(aiProcess_JoinIdenticalVertices)
  case Triangulate extends PostProcessingFlag(aiProcess_Triangulate)
  // ...

class PostProcessingFlags(fs: PostProcessingFlag*) extends Flags[PostProcessingFlag](fs: _*)