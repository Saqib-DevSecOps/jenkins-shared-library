#!/usr/bin/env groovy

package com.exarth.jenkinslibrary

class DependencyCheckScanner implements Serializable {

    def script

    DependencyCheckScanner(script) {
        this.script = script
    }

    // 📂 Method for scanning project files
    void scanProjectFiles(String projectDir = '.', String outputDir = 'dependency-check-report') {
        script.echo "🔍 Scanning project files in directory: ${projectDir}..."
        script.sh """
            dependency-check --project "Project" --scan ${projectDir} --out ${outputDir}
        """
        script.echo "✅ Project files scan completed. Report saved in: ${outputDir}."
    }

    // 🐋 Method for scanning Docker images
    void scanDockerImage(String imageName, String outputDir = 'dependency-check-report') {
        script.echo "🔍 Scanning Docker image: ${imageName}..."
        script.sh """
            docker run --rm -v /var/run/docker.sock:/var/run/docker.sock \
                owasp/dependency-check --scan ${imageName} --out ${outputDir}
        """
        script.echo "✅ Docker image scan completed. Report saved in: ${outputDir}."
    }
}
