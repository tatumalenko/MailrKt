plugins {
  kotlin("js") version "1.6.10"
}

group = "org.example"
version = "1.0.1"

repositories {
  mavenCentral()
  jcenter()
}

dependencies {
  implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.322-kotlin-1.6.10")
  implementation("org.jetbrains.kotlin-wrappers:kotlin-extensions:1.0.1-pre.322-kotlin-1.6.10")
  implementation("com.github.ojaynico:ojaynico-kotlin-react-native:1.1.9")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
}

kotlin {
  js {
    nodejs()
    useCommonJs()
    binaries.executable()
  }
}

// Delete package.json in build directory to prevent jest-haste-map: Haste module naming collision error
task("deletePackages") {
  doLast {
    val fileTree = fileTree("$rootDir").matching {
      include("**/build/js/packages_imported/ojaynico-kotlin-react-native-js-legacy/1.1.9/package.json")
      include("**/build/tmp/**/package.json")
    }
    println("Deleting files: ${fileTree.files.fold("") { acc, file -> "$acc\n$file" }}")
    val result = delete(fileTree)
    println("Files deleted: $result")
  }
}

// Copy Resources from resources directory into build directory
task("copyResources") {
  doLast {
    copy {
      from("src/main/resources")
      into("build/js/packages/shared/kotlin/resources")
    }
  }
}

// react-native package contains a performance logger that calls on `global.performance.now` without being defined yet,
// but it is defined in setUpPerformance.js, so we include the import statement in the createPerformanceLogger.js file
// if it is present.
task("addSetUpPerformanceRequireStatement") {
  doLast {
    val file = File("$rootDir/build/js/node_modules/react-native/Libraries/Utilities/createPerformanceLogger.js")
    if (file.exists()) {
      val lines = file.readLines().toMutableList()
      val index = lines.indexOfFirst { it.contains("require(") }
      lines.add(index, "require('../Core/setUpPerformance');")
      file.writeText(lines.joinToString("\n"))
    }
  }
}

// Copy resources from resources during assemble task
tasks {
  assemble {
    finalizedBy("copyResources")
    finalizedBy("deletePackages")
    finalizedBy("addSetUpPerformanceRequireStatement")
  }
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
  rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().download =
    false // or true for default behavior
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
  rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().download =
    false // or true for default behavior
}
