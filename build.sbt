ThisBuild / version := "1.0"

ThisBuild / scalaVersion := "3.6.3"

lazy val root = (project in file(".")).settings(name := "ScalaLwjglTest")

val lwjglVersion = "3.3.4"
val lwjglNatives = "natives-windows"

val jcudaVersion = "12.0.0"

val lwjglModules = Array(
  "lwjgl",
  "lwjgl-assimp",
  "lwjgl-freetype",
  "lwjgl-glfw",
  "lwjgl-msdfgen",
  "lwjgl-nanovg",
  "lwjgl-openal",
  "lwjgl-opengl",
  "lwjgl-par",
  "lwjgl-stb",
)

val jcudaModules = Array(
  "jcuda", 
  "jcublas", 
  "jcudnn", 
  "jcufft", 
  "jcurand", 
  "jcusolver", 
  "jcusparse"
)

libraryDependencies += "org.lwjgl" % "lwjgl" % "3.3.4"
libraryDependencies += "org.lwjgl" % "lwjgl" % "3.3.4" classifier s"natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-assimp" % "3.3.4"
libraryDependencies += "org.lwjgl" % "lwjgl-assimp" % "3.3.4" classifier s"natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-glfw" % "3.3.4"
libraryDependencies += "org.lwjgl" % "lwjgl-glfw" % "3.3.4" classifier s"natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-nanovg" % "3.3.4"
libraryDependencies += "org.lwjgl" % "lwjgl-nanovg" % "3.3.4" classifier s"natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-opengl" % "3.3.4"
libraryDependencies += "org.lwjgl" % "lwjgl-opengl" % "3.3.4" classifier s"natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-par" % "3.3.4"
libraryDependencies += "org.lwjgl" % "lwjgl-par" % "3.3.4" classifier s"natives-windows"
libraryDependencies += "org.lwjgl" % "lwjgl-stb" % "3.3.4"
libraryDependencies += "org.lwjgl" % "lwjgl-stb" % "3.3.4" classifier s"natives-windows"

libraryDependencies += "org.joml" % "joml" % "1.10.8"
libraryDependencies += "org.joml" % "joml-primitives" % "1.10.0"

libraryDependencies += "org.jcuda" % "jcuda" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcuda-natives" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcublas" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcublas-natives" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcudnn" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcudnn-natives" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcufft" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcufft-natives" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcurand" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcurand-natives" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcusolver" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcusolver-natives" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcusparse" % "12.0.0"
libraryDependencies += "org.jcuda" % "jcusparse-natives" % "12.0.0"

libraryDependencies += "com.github.quickhull3d" % "quickhull3d" % "1.0.0"
libraryDependencies += "net.jafama" % "jafama" % "2.3.2"
