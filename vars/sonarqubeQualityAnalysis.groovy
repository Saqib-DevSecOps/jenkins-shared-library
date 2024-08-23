#!/usr/bin/env groovy

import com.exarth.jenkinslibrary.SonarQubeQualityAnalysis

// vars/sonarqubeQualityAnalysis.groovy
def call(Map config = [:] as Map) {
    def sonarqube = new SonarQubeQualityAnalysis(this, config)
    sonarqube.runAnalysis()
}

def checkQualityGate(Map config = [:] as Map, boolean abortOnFailure = true) {
    def sonarqube = new SonarQubeQualityAnalysis(this, config)
    sonarqube.checkQualityGate(abortOnFailure)
}

