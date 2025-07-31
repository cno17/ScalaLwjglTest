package yage.scene.grid

import scala.util.Random

/**
 * i: column index
 * j: row index
 * (posX, posY): west south corner
 */

class Grid(val tileNumX: Int, val tileNumY: Int, tileExtX: Int, val tileExtY: Int):

  val extX = tileNumX * tileExtX
  val extY = tileNumY * tileExtY

  val tiles = Array.fill(tileNumX, tileNumY)(Tile(tileExtX, tileExtY))

  val rnd = Random()

  private var indI = 0 // west
  private var indJ = 0 // south

  init()

  def init() =
    for i <- 0 to tileNumX - 1 do
      for j <- 0 to tileNumY - 1 do
        val t = tiles(i)(j)
        t.posX = i * tileExtX
        t.posY = j * tileExtY
        t.westNeighbour = tiles(modI(i - 1))(j)
        t.eastNeighbout = tiles(modI(i + 1))(j)
        t.southNeighbour = tiles(i)(modJ(j - 1))
        t.northNeighbour = tiles(i)(modJ(j + 1))

  def apply(i: Int, j: Int) = tiles(modI(i))(modJ(j))
  
  def posX = this (indI, indJ).posX
  def posY = this (indI, indJ).posY

  def cenX = centerTile.cenX
  def cenY = centerTile.cenY

  def contains(x: Float, y: Float) =
    posX <= x && x < posX + extX && posY <= y && y < posY + extY

  def centerTile =
    val i = indI + tileNumX / 2
    val j = indJ + tileNumY / 2
    this (i, j)

  def randomTile =
    val i = indI + rnd.nextInt() % tileNumX
    val j = indJ + rnd.nextInt() % tileNumY
    this (i, j)

  def tileAt(x: Float, y: Float) = 0 // TODO

  def scrollEast() =
    forEachTileInColumn(indI, _.translate(extX, 0))
    indI = modI(indI + 1)

  def scrollWest() =
    forEachTileInColumn(indI + tileNumX - 1, _.translate(-extX, 0))
    indI = modI(indI - 1)
  
  def scrollNorth() =
    forEachTileInRow(indJ, _.translate(0, extY))
    indJ = modJ(indJ + 1)

  def scrollSouth() =
    forEachTileInRow(indJ + tileNumY - 1, _.translate(0, -extY))
    indJ = modJ(indJ - 1)

  def forEachTile(f: Tile => Unit) =
    for i <- 0 to tileNumX - 1 do
      for j <- 0 to tileNumY - 1 do
        f(this (i, j))

  def forEachTileInColumn(i: Int, f: Tile => Unit) =
    for j <- 0 to tileNumY - 1 do
      f(this (i, j))

  def forEachTileInRow(j: Int, f: Tile => Unit) =
    for i <- 0 to tileNumY - 1 do
      f(this (i, j))

  override def toString() =
    val sb = StringBuilder()
    for j <- indJ + tileNumY - 1 to indJ by -1 do
      for i <- indI to indI + tileNumX - 1 do
        val t = this (i, j)
        sb.append(s"($i, $j) = (${t.posX}, ${t.posY})   ")
      sb.append("\n")
    sb.toString()

  private def modI(i: Int) = if i < 0 then i + tileNumX else i % tileNumX
  private def modJ(i: Int) = if i < 0 then i + tileNumY else i % tileNumY


// def modI2(i: Int) = if (i > 0) i % tileCountX else tileCountX - (-i % tileCountX)
