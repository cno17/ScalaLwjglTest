package test.scene.grid

import yage.scene.grid.Grid
import yage.scene.grid.Tile

object GridTest:

  def main(args: Array[String]) =
    val g = Grid(3, 3, 1, 1)
    println(g)
    g.scrollEast()
    println(g)
    g.scrollNorth()
    println(g)
