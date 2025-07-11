/*
 * Copyright 2017-2023 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENCE file.
 */

import org.gradle.jvm.tasks.Jar
import java.net.URI

plugins {
    `maven-publish`
    signing
}

publishing {
    repositories {
        configureMavenPublication(project)
    }

    val javadocJar = project.configureEmptyJavadocArtifact()
    publications.withType(MavenPublication::class).all {
        pom.configureMavenCentralMetadata(project)
        signPublicationIfKeyPresent(project, this)
        artifact(javadocJar)
    }

    tasks.withType<PublishToMavenRepository>().configureEach {
        dependsOn(tasks.withType<Sign>())
    }
}

fun MavenPom.configureMavenCentralMetadata(project: Project) {
    name = project.name
    description = "Amplify"
    url = "https://github.com/laynepenney/AmplifyKMP"

    licenses {
        license {
            name = "Apache-2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            distribution = "repo"
        }
    }

    developers {
        developer {
            id = "Layne"
            name = "Layne Penney"
            organization = "Layne.Pro"
            organizationUrl = "https://layne.pro"
        }
    }

    scm {
        url = "https://github.com/laynepenney/AmplifyKMP"
    }
}

fun MavenPublication.mavenCentralArtifacts(project: Project, sources: SourceDirectorySet) {
    val sourcesJar by project.tasks.creating(Jar::class) {
        archiveClassifier = "sources"
        from(sources)
    }
    val javadocJar by project.tasks.creating(Jar::class) {
        archiveClassifier = "javadoc"
        // contents are deliberately left empty
    }
    artifact(sourcesJar)
    artifact(javadocJar)
}


fun mavenRepositoryUri(): URI {
    val repositoryId: String? = System.getenv("libs.repository.id")
    return if (repositoryId == null) {
        URI("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
    } else {
        URI("https://oss.sonatype.org/service/local/staging/deployByRepositoryId/$repositoryId")
    }
}

fun RepositoryHandler.configureMavenPublication( project: Project) {
    maven {
        url = mavenRepositoryUri()
        credentials {
            username = project.getSensitiveProperty("libs.sonatype.user")
            password = project.getSensitiveProperty("libs.sonatype.password")
        }
    }

    // Something that's easy to "clean" for development not mavenLocal
    maven(project.rootProject.layout.buildDirectory.dir("repo")) {
        name = "buildRepo"
    }
}

fun Project.configureEmptyJavadocArtifact(): Jar {
    val javadocJar by project.tasks.creating(Jar::class) {
        archiveClassifier = "javadoc"
        // contents are deliberately left empty
    }
    return javadocJar
}

fun signPublicationIfKeyPresent(project: Project, publication: MavenPublication) {
    val keyId = project.getSensitiveProperty("libs.sign.key.id")
    val signingKey = project.getSensitiveProperty("libs.sign.key.private")
    val signingKeyPassphrase = project.getSensitiveProperty("libs.sign.passphrase")
    if (!signingKey.isNullOrBlank()) {
        project.extensions.configure<SigningExtension>("signing") {
            useInMemoryPgpKeys(keyId, signingKey, signingKeyPassphrase)
            sign(publication)

            // Temporary workaround, see https://github.com/gradle/gradle/issues/26091#issuecomment-1722947958
            tasks.withType<AbstractPublishToMaven>().configureEach {
                val signingTasks = tasks.withType<Sign>()
                mustRunAfter(signingTasks)
            }
        }
    }
}

fun Project.getSensitiveProperty(name: String): String? {
    return project.findProperty(name) as? String ?: System.getenv(name)
}
