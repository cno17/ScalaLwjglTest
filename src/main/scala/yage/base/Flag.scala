package yage.base


trait Flag:
  
  def id: Int


trait FlagFinder[F <: Flag]:
  
  def values: Array[F]
  def apply(id: Int) = values.find(_.id == id).get


class Flags[F <: Flag](fs: F*):

  var value = 0
  
  add(fs: _*)
  
  def clear() = value = 0
  def add(fs: F*) = for f <- fs do value |= f.id
  def remove(fs: F*) = for f <- fs do value &= ~f.id
  def set(fs: F*) = {clear(); add(fs: _*)}
  def contains(f: F) = (value & f.id) != 0

  def += (fs: F*) = add(fs: _*)
  def -= (fs: F*) = remove(fs: _*)
  def := (fs: F*) = set(fs: _*)