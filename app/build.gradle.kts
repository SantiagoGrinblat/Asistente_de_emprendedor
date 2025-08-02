import org.gradle.kotlin.dsl.implementation

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.jetbrains.kotlin.serialization)
  alias(libs.plugins.ksp)
}

android {
  namespace = "com.santidev.entrepreneurassistant"
  compileSdk = 35
  
  defaultConfig {
    applicationId = "com.santidev.entrepreneurassistant"
    minSdk = 28
    targetSdk = 35
    versionCode = 1
    versionName = "1.0"
    
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
  
  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    compose = true
  }
}

dependencies {
  
  //Navegacion
  implementation(libs.androidx.navigation.compose)
  
  //serializacion
  implementation(libs.kotlinx.serialization.json)
  
  //iconos
  implementation(libs.androidx.material.icons.extended)
  
  //Room
  implementation(libs.room.runtime)
  implementation(libs.room.ktx)
  ksp(libs.room.compiler)
  
  //Koin
  implementation(project.dependencies.platform(libs.koin.bom))
  implementation(libs.koin.android)
  implementation(libs.koin.core)
  implementation(libs.koin.androidx.compose)
  
  //AppCompact
  implementation(libs.androidx.appcompat)
  
  //coil
  implementation (libs.coil.compose)
  
  //permisos
  implementation (libs.accompanist.permissions)
  
  implementation(libs.kotlinx.coroutines.android)
  
  implementation (libs.androidx.lifecycle.viewmodel.compose)
  
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}