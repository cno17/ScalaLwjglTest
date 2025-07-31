package yage.scene.grid


// (posX, posY) = position of west south corner

class Tile(var posX: Int, var posY: Int, val extX: Int, val extY: Int):
  
  def this(extX: Int, extY: Int) = this(0, 0, extX, extY)

  var westNeighbour: Tile = null
  var eastNeighbout: Tile = null
  var southNeighbour: Tile = null
  var northNeighbour: Tile = null
  
  def cenX = posX + extX / 2
  def cenY = posY + extY / 2

  def contains(x: Float, y: Float) =
    posX <= x && x < posX + extX && posY <= y && y < posY + extY

  def translate(x: Int, y: Int) =
    posX += x
    posY += y
  
  override def toString() = s"Tile(px = $posX, py = $posY)"
