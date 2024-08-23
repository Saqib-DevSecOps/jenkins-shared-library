#!/usr/bin/env groovy

package com.example

class SonarQubeQualityAnalysis implements Serializable {

    def script
    def projectName
    def projectKey
    def additionalArgs

    SonarQubeQualityAnalysis(script, Map config = [:] as Map) {
        this.script = script
        this.projectName = config.projectName ?: 'Default Project'
        this.projectKey = config.projectKey ?: 'Default Key'
        this.additionalArgs = config.additionalArgs ?: ''
    }

    // 🚀 Method to run the SonarQube scan
    void runAnalysis() {
        script.echo "🛠️ Starting SonarQube analysis for project: ${projectName}..."
        def scannerHome = script.tool 'SonarQube Scanner'

        script.withSonarQubeEnv('SonarQube') {
            script.sh """
                ${scannerHome}/bin/sonar-scanner \
                -Dsonar.projectName=${projectName} \
                -Dsonar.projectKey=${projectKey} \
                ${additionalArgs}
            """
        }
        script.echo "✅ SonarQube analysis completed for project: ${projectName}."
    }

    // 🔍 Method to check the SonarQube Quality Gate with an option to continue or abort the pipeline
    void checkQualityGate(boolean abortOnFailure = true) {
        script.echo "🔍 Checking SonarQube Quality Gate..."
        script.timeout(time: 10, unit: 'MINUTES') {
            def qg = script.waitForQualityGate()
            if (qg.status != 'OK') {
                script.echo "❌ Quality Gate failed: ${qg.status}"
                if (abortOnFailure) {
                    script.error "🚫 Pipeline aborted due to quality gate failure: ${qg.status}"
                } else {
                    script.echo "⚠️ Continuing pipeline despite quality gate failure..."
                }
            } else {
                script.echo "🎉 Quality Gate passed: ${qg.status}"
            }
        }
    }
}
