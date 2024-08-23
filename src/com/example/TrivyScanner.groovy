#!/usr/bin/env groovy

package com.example

class TrivyScanner implements Serializable {

    def script

    TrivyScanner(script) {
        this.script = script
    }

    // 🧑‍💻 Method for scanning source code
    void scanCode(String projectDir = '.') {
        script.echo "🔍 Scanning source code in directory: ${projectDir}..."
        script.sh "trivy fs ${projectDir} --security-checks vuln,config,secret"
        script.echo "✅ Source code scan completed."
    }

    // 📁 Method for scanning the project file system
    void scanFileSystem(String projectDir = '.') {
        script.echo "🔍 Scanning file system in directory: ${projectDir}..."
        script.sh "trivy fs ${projectDir}"
        script.echo "✅ File system scan completed."
    }

    // 🐋 Method for scanning Docker images
    void scanDockerImage(String imageName) {
        script.echo "🔍 Scanning Docker image: ${imageName}..."
        script.sh "trivy image ${imageName}"
        script.echo "✅ Docker image scan completed."
    }
}
