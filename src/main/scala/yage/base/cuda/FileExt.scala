package yage.base.cuda

import org.lwjgl.BufferUtils.createByteBuffer

import java.io.File
import java.io.FileInputStream
import scala.io.Source

// todo: getDescendants, foreachDescendant, return options

trait FileExt:

  extension (f: File)

    def shortName =
      val s = f.getName
      val i = s.indexOf('.')
      if f.isDirectory || i == -1 then s else s.substring(0, i)

    def extension =
      val s = f.getName
      val i = s.indexOf('.')
      if f.isDirectory || i == -1 then "" else s.substring(i + 1)

    def bytes =
      val is = FileInputStream(f)
      val ba = is.readAllBytes()
      is.close()
      createByteBuffer(ba.length).put(ba).rewind()

    def chars =
      val src = Source.fromFile(f)
      val res = src.getLines.mkString("\n")
      src.close()
      res

    def lines =
      val src = Source.fromFile(f)
      val res = src.getLines.toArray
      src.close()
      res
