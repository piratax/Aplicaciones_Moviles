plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt") //Agregado
    id("dagger.hilt.android.plugin") //Agregado
}

android {
    namespace = "pe.edu.upeu.asistenciaupeujc"
    compileSdk = 34

    defaultConfig {
        applicationId = "pe.edu.upeu.asistenciaupeujc"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        //Agregado Inicio
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
               // arguments += ["room.schemaLocation":
                //"$projectDir/schemas".toString()]
            }
        }
        //Agregado Fin

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"//1.8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class") //Agregado
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    val appcompat_version = "1.6.1"
    implementation("androidx.appcompat:appcompat:$appcompat_version") //Agregado recien

    //agregado Navigation
    val navCompose = "2.7.1" //old 2.7.0-beta01
    implementation("androidx.navigation:navigation-compose:$navCompose")
    implementation ("com.google.accompanist:accompanist-insets:0.31.4-beta")//Agregado //0.17.0
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.31.4-beta") //0.16.0
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.31.4-beta") //0.16.0

    //Agregados
    implementation ("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-compiler:2.47")

//Agregado Dagger - Hilt
    implementation ("com.google.dagger:hilt-android:2.47")
    kapt ("com.google.dagger:hilt-compiler:2.47")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.valentinilk.shimmer:compose-shimmer:1.0.5")
    implementation ("io.coil-kt:coil-compose:2.4.0")
//Otros adicionales compose
    implementation ("androidx.compose.ui:ui-tooling")
    implementation ("androidx.compose.foundation:foundation")
    implementation ("androidx.compose.runtime:runtime-livedata")
//Para trabajar con contraint layout
    implementation ("androidx.constraintlayout:constraintlayout:2.2.0-alpha10")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10")
//Para trabajar con camara
    val camerax_version = "1.2.3"
    implementation ("androidx.camera:camera-core:${camerax_version}")
    implementation ("androidx.camera:camera-camera2:${camerax_version}")
    implementation ("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation ("androidx.camera:camera-view:${camerax_version}")

//Barcode
    implementation ("com.google.mlkit:barcode-scanning:17.1.0")
//Location
    implementation ("com.google.android.gms:play-services-location:21.0.1")
//Manager permissions
    implementation ("com.google.accompanist:accompanist-permissions:0.30.1")
//Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation ("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    kapt ("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
//Formularios
    implementation ("com.github.k0shk0sh:compose-easyforms:0.2.0")
// Room https://developer.android.com/jetpack/androidx/releases/room
    val room_version = "2.5.2"
    implementation ("androidx.room:room-runtime:$room_version")
    annotationProcessor ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
// Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
}