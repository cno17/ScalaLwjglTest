package demo.scene.shadow

import org.joml.Vector3f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20
import org.lwjgl.system.MemoryUtil
import java.io.IOException
import java.nio.FloatBuffer

object DemoUtils:
  
    private val VECTOR_MINUS_ONE = Vector3f(-1.0f, -1.0f, -1.0f)
    private val VECTOR_PLUS_ONE = Vector3f(1.0f, 1.0f, 1.0f)

    /**
     * Write the vertices (position and normal) of an axis-aligned unit box into
     * the provided [FloatBuffer].
     *
     * @param fv
     * the [FloatBuffer] receiving the vertex position and
     * normal
     */
    def triangulateUnitBox(fv: FloatBuffer) =
        triangulateBox(VECTOR_MINUS_ONE, VECTOR_PLUS_ONE, fv)
    

    /**
     * Write the vertices (position and normal) of an axis-aligned box with the
     * given corner coordinates into the provided [FloatBuffer].
     *
     * @param min
     * the min corner
     * @param max
     * the max corner
     * @param fv
     * the [FloatBuffer] receiving the vertex position and
     * normal
     */
    def triangulateBox(min: Vector3f, max: Vector3f, fv: FloatBuffer) = 
        /* Front face */
        fv.put(min.x).put(min.y).put(max.z).put(0.0f).put(0.0f).put(1.0f)
        fv.put(max.x).put(min.y).put(max.z).put(0.0f).put(0.0f).put(1.0f)
        fv.put(max.x).put(max.y).put(max.z).put(0.0f).put(0.0f).put(1.0f)
        fv.put(max.x).put(max.y).put(max.z).put(0.0f).put(0.0f).put(1.0f)
        fv.put(min.x).put(max.y).put(max.z).put(0.0f).put(0.0f).put(1.0f)
        fv.put(min.x).put(min.y).put(max.z).put(0.0f).put(0.0f).put(1.0f)
        /* Back face */
        fv.put(max.x).put(min.y).put(min.z).put(0.0f).put(0.0f).put(-1.0f)
        fv.put(min.x).put(min.y).put(min.z).put(0.0f).put(0.0f).put(-1.0f)
        fv.put(min.x).put(max.y).put(min.z).put(0.0f).put(0.0f).put(-1.0f)
        fv.put(min.x).put(max.y).put(min.z).put(0.0f).put(0.0f).put(-1.0f)
        fv.put(max.x).put(max.y).put(min.z).put(0.0f).put(0.0f).put(-1.0f)
        fv.put(max.x).put(min.y).put(min.z).put(0.0f).put(0.0f).put(-1.0f)
        /* Left face */
        fv.put(min.x).put(min.y).put(min.z).put(-1.0f).put(0.0f).put(0.0f)
        fv.put(min.x).put(min.y).put(max.z).put(-1.0f).put(0.0f).put(0.0f)
        fv.put(min.x).put(max.y).put(max.z).put(-1.0f).put(0.0f).put(0.0f)
        fv.put(min.x).put(max.y).put(max.z).put(-1.0f).put(0.0f).put(0.0f)
        fv.put(min.x).put(max.y).put(min.z).put(-1.0f).put(0.0f).put(0.0f)
        fv.put(min.x).put(min.y).put(min.z).put(-1.0f).put(0.0f).put(0.0f)
        /* Right face */
        fv.put(max.x).put(min.y).put(max.z).put(1.0f).put(0.0f).put(0.0f)
        fv.put(max.x).put(min.y).put(min.z).put(1.0f).put(0.0f).put(0.0f)
        fv.put(max.x).put(max.y).put(min.z).put(1.0f).put(0.0f).put(0.0f)
        fv.put(max.x).put(max.y).put(min.z).put(1.0f).put(0.0f).put(0.0f)
        fv.put(max.x).put(max.y).put(max.z).put(1.0f).put(0.0f).put(0.0f)
        fv.put(max.x).put(min.y).put(max.z).put(1.0f).put(0.0f).put(0.0f)
        /* Top face */
        fv.put(min.x).put(max.y).put(max.z).put(0.0f).put(1.0f).put(0.0f)
        fv.put(max.x).put(max.y).put(max.z).put(0.0f).put(1.0f).put(0.0f)
        fv.put(max.x).put(max.y).put(min.z).put(0.0f).put(1.0f).put(0.0f)
        fv.put(max.x).put(max.y).put(min.z).put(0.0f).put(1.0f).put(0.0f)
        fv.put(min.x).put(max.y).put(min.z).put(0.0f).put(1.0f).put(0.0f)
        fv.put(min.x).put(max.y).put(max.z).put(0.0f).put(1.0f).put(0.0f)
        /* Bottom face */
        fv.put(min.x).put(min.y).put(min.z).put(0.0f).put(-1.0f).put(0.0f)
        fv.put(max.x).put(min.y).put(min.z).put(0.0f).put(-1.0f).put(0.0f)
        fv.put(max.x).put(min.y).put(max.z).put(0.0f).put(-1.0f).put(0.0f)
        fv.put(max.x).put(min.y).put(max.z).put(0.0f).put(-1.0f).put(0.0f)
        fv.put(min.x).put(min.y).put(max.z).put(0.0f).put(-1.0f).put(0.0f)
        fv.put(min.x).put(min.y).put(min.z).put(0.0f).put(-1.0f).put(0.0f)
    
