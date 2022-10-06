plugins {
  `kotlin-dsl`
}

repositories {
  mavenCentral()
  google()
}

dependencies  {
  // workaround for https://github.com/gradle/gradle/issues/15383
  implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
  implementation(libs.agp.bundle.gradle)
  implementation(libs.kotlin.gradle)
}