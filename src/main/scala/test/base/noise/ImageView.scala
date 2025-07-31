package test.base.noise

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JPanel

class ImageView(var image: BufferedImage) extends JPanel:

  def setImage(img: BufferedImage) =
    image = img
    repaint()
  
  override def paint(g: Graphics) =
    g.drawImage(image, 10, 10, null) 

