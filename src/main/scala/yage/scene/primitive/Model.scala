package yage.scene.primitive

import org.joml.Vector2f
import org.joml.Vector4f
import org.lwjgl.assimp.AIMesh
import org.lwjgl.assimp.Assimp.*
import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_2
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.Normal
import yage.base.opengl.primitive.Attribute.Semantics.Position
import yage.base.opengl.primitive.Attribute.Semantics.Tangent
import yage.base.opengl.primitive.Attribute.Semantics.TexCoord
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.primitive.Primitive.Mode
import yage.base.opengl.primitive.Vertex
import yage.base.opengl.primitive.VertexDescriptor
import yage.scene.primitive.Model.MVertex

import java.io.File
import java.nio.ByteBuffer

object Model:

  class MVertex(val pos: Vector4f, val nor: Vector4f, val tan: Vector4f, val tec: Vector2f) extends Vertex[MVertex]:

    override def load(buf: ByteBuffer) =
      pos.set(buf)
      nor.set(buf)
      tan.set(buf)
      tec.set(buf)
      this

    override def store(buf: ByteBuffer) =
      pos.store(buf)
      nor.store(buf)
      tan.store(buf)
      tec.store(buf)
      buf

/**
 * Assumption: The model file contains positions, normals and texture coordinates.
 * ToDo: 
 *   scale
 *   use PostProcessingFlags
 *   
 *   import materials: diffuse map, specular map! 
 *   thats not model stuff
 *   MaterialImporter!
 * 
 */

class Model(f: File) extends Primitive:

  type V = MVertex

  override def init() =
    val flags = aiProcess_Triangulate | aiProcess_CalcTangentSpace | aiProcess_GenBoundingBoxes
    val scene = aiImportFile(f.getAbsolutePath, flags)
    val mesh = AIMesh.create(scene.mMeshes.get(0))
    assert(mesh.mNormals() != null, s"No normals found in model file $f")
    assert(mesh.mTextureCoords(0) != null, s"No texture coordinates found in model file $f")
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    val d2 = Descriptor(2, F32_4, Tangent)
    val d3 = Descriptor(4, F32_2, TexCoord)
    mode = Mode.Triangles
    descriptor = VertexDescriptor(d0, d1, d2, d3)
    vertices = verticesOf(mesh)
    indices = indicesOf(mesh)

  private def verticesOf(mesh: AIMesh) =
    val numV = mesh.mNumVertices()
    val posB = mesh.mVertices()
    val norB = mesh.mNormals()
    val tanB = mesh.mTangents()
    val tecB = mesh.mTextureCoords(0)
    val res = new Array[MVertex](numV)
    for i <- 0 to numV - 1 do
      val pos1 = posB.get(i)
      val nor1 = norB.get(i)
      val tan1 = tanB.get(i)
      val tec1 = tecB.get(i)
      val pos2 = Vector4f(pos1.x, pos1.y, pos1.z, 1f)
      val nor2 = Vector4f(nor1.x, nor1.y, nor1.z, 0f)
      val tan2 = Vector4f(tan1.x, tan1.y, tan1.z, 0f)
      val tec2 = Vector2f(tec1.x, tec1.y)
      res(i) = MVertex(pos2, nor2, tan2, tec2)
    res

  private def indicesOf(mesh: AIMesh) =
    val nf = mesh.mNumFaces()
    val fs = mesh.mFaces()
    val res = new Array[Int](nf * 3)
    for i <- 0 to nf - 1 do
      val is = fs.get(i).mIndices()
      res(3 * i + 0) = is.get(0)
      res(3 * i + 1) = is.get(1)
      res(3 * i + 2) = is.get(2)
    res
