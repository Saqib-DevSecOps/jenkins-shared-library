#!/usr/bin/env groovy

import com.exarth.jenkinslibrary.TrivyScanner

def scanCode(Map config = [:]) {
    def trivy = new TrivyScanner(this)
    trivy.scanCode(config.projectDir as String)
}

def scanFileSystem(Map config = [:]) {
    def trivy = new TrivyScanner(this)
    trivy.scanFileSystem(config.projectDir  as String)
}

def scanDockerImage(Map config = [:]) {
    def trivy = new TrivyScanner(this)
    trivy.scanDockerImage(config.imageName as String)
}
