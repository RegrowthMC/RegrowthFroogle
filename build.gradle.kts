plugins {
    `java-library`
    id("com.gradleup.shadow") version("9.3.1")
    id("xyz.jpenilla.run-paper") version("3.0.2")
}

group = "org.lushplugins"
version = "1.1.0"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.papermc.io/repository/maven-public/") // Paper
    maven("https://repo.lushplugins.org/snapshots/") // LushLib
    maven("https://repo.fancyinnovations.com/releases") // FancyNPCs
}

dependencies {
    // Dependencies
    compileOnly("io.papermc.paper:paper-api:1.21.7-R0.1-SNAPSHOT")

    // Soft Dependencies
    compileOnly("org.lushplugins:ItemPools:2.1.0")
    compileOnly("de.oliver:FancyNpcs:2.9.2")

    // Libraries
    implementation("org.lushplugins:LushLib:0.10.89")
    implementation("org.lushplugins:PlaceholderHandler:1.0.0-alpha10")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))

    registerFeature("optional") {
        usingSourceSet(sourceSets["main"])
    }

    withSourcesJar()
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.add("-parameters")
    }

    shadowJar {
        minimize()

        archiveFileName.set("${project.name}-${project.version}.jar")
    }

    processResources{
        filesMatching("plugin.yml") {
            expand(project.properties)
        }

        inputs.property("version", rootProject.version)
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
    }

    runServer {
        minecraftVersion("1.21.11")

        downloadPlugins {
            github("RegrowthMC", "RegrowthEntityVariants", "v1.0.1", "RegrowthEntityVariants-1.0.1.jar")
            github("OakLoaf", "ItemPools", "v2.1.1", "ItemPools-2.1.1.jar")
            modrinth("fancynpcs", "2.9.2.337")
            modrinth("placeholderapi", "UmbIiI5H")
            modrinth("viaversion", "5.7.1")
            modrinth("viabackwards", "5.7.1")
        }
    }
}
