plugins {
    id("org.jetbrains.compose") version "1.3.0-alpha01-dev824"
    kotlin("multiplatform") version "1.7.20"
    id("org.jetbrains.gradle.apple.applePlugin") version "222.3345.143-0.17-dev-1"
}

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    if (System.getProperty("os.arch") == "aarch64") {
        iosSimulatorArm64("uikitSimArm64") {
            binaries {
                framework {
                    baseName = "shared"
                    freeCompilerArgs += listOf(
                        "-linker-option", "-framework", "-linker-option", "Metal",
                        "-linker-option", "-framework", "-linker-option", "CoreText",
                        "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                    )
                }
            }
        }
    } else {
        ios("uikitX64") {
            binaries {
                framework {
                    baseName = "shared"
                    freeCompilerArgs += listOf(
                        "-linker-option", "-framework", "-linker-option", "Metal",
                        "-linker-option", "-framework", "-linker-option", "CoreText",
                        "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                    )
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
                implementation("org.jetbrains.skiko:skiko:0.7.36")
            }
        }
        val uikitMain by creating {  }
        if (System.getProperty("os.arch") == "aarch64") {
            val uikitSimArm64Main by getting { dependsOn(uikitMain) }
        } else {
            val uikitX64Main by getting { dependsOn(uikitMain) }
        }
    }
}

apple {
    iosApp {
        productName = "ComposeAppCode"
        sceneDelegateClass = "SceneDelegate"
        launchStoryboard = "LaunchScreen"
        dependencies {
//            implementation(project(":")) //TODO also I tried to remove this dependency
        }
    }
}
