package test.base.nanovg

import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgText
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.state.Font
import yage.base.nanovg.util.Box

object TextTest extends GlApp:

  var text1 = "Hello world Joey!"
  var text2 = "Hello Africa, America, Asia, Australia and Europe!"
  var color1: vgColor = null
  var color2: vgColor = null
  var font1: Font = null
  var font2: Font = null

  override def init() =
    color1 = vgColor(0.5f, 0.8f, 0.5f)
    color2 = vgColor(0.5f, 0.5f, 0.8f)
    font1 = Font(resFile("Fonts/Cousine-Regular.ttf"))
    font2 = Font(resFile("Fonts/Roboto-Bold.ttf"))
    vgContext.setClearColor(vgColor(0.0f, 0.0f, 0.0f))
    // vgContext.setPenWidth(1f)
    vgContext.setTextLetterSpacing(10f)

  override def draw() =
    vgContext.beginFrame(Window.sizeX, Window.sizeX)

    // nvgTextAlign(ctx, NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE)

    vgContext.setFont(font1)
    vgContext.setFontSize(20)
    // vgText.getTextBounds(100, 100, text1, bound)
    vgContext.setBrush(color1)
    // bound.fill()
    vgContext.setBrush(color2)
    vgText.fillText(100, 100, text1)
//
//    Context.path.begin()
//    Context.path.rect(box)
//    Context.stroke()
//
//    Context.setFillColor(color2)
//    Context.setFont(font2)
//    Context.setFontSize(20.0f)
//    Context.text.drawTextBox(100, 300, 200, text2)


    vgContext.endFrame()
