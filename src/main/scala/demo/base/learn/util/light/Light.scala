package demo.base.learn.util.light

class Light:

  var intensity = ADS()
  
  var offsetIntensityA = 0
  var offsetIntensityD = 0
  var offsetIntensityS = 0
  
  init()
  
  def init() =
    intensity.a.set(0.1f)
    intensity.d.set(1.0f)
    intensity.s.set(1.0f)
    
