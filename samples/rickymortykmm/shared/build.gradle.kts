plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
           dependencies {
               implementation(Deps.Kotlin.jsonSerialization)
               implementation(Deps.Ktor.core)
               implementation(Deps.Ktor.content)
               implementation(Deps.Ktor.serializationJson)
               implementation(Deps.Ktor.logging)
               implementation(Deps.Koin.core)
               implementation(Deps.Kotlin.serializationCore)
               implementation(Deps.Kotlin.coroutinesCore)
               implementation("io.github.behzodhalil:meteor-mvi:0.0.6")
           }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosMain by creating
        val iosX64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {}
    }
}

android {
    namespace = "io.spherelabs.rickymortykmm"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}
