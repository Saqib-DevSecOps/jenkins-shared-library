#!/usr/bin/env groovy

import com.example.DependencyCheckScanner

// vars/dependencyCheckScanner.groovy
def scanProjectFiles(Map config = [:]) {
    def dependencyCheck = new DependencyCheckScanner(this)
    dependencyCheck.scanProjectFiles(config.projectDir as String, config.outputDir as String)
}

def scanDockerImage(Map config = [:]) {
    def dependencyCheck = new DependencyCheckScanner(this)
    dependencyCheck.scanDockerImage(config.imageName as String, config.outputDir as String)
}
