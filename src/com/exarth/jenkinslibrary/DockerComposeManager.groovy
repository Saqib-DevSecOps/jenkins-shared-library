#!/usr/bin/env groovy

package com.exarth.jenkinslibrary


class DockerComposeManager implements Serializable {

    def script

    DockerComposeManager(script) {
        this.script = script
    }

    // 🛠️ Method to start Docker Compose services
    void startServices(String composeFile = 'docker-compose.yml') {
        script.echo "🚀 Starting Docker Compose services using file: ${composeFile}..."
        script.sh "docker-compose -f ${composeFile} up -d"
        script.echo "✅ Docker Compose services started."
    }

    // 🛑 Method to stop Docker Compose services
    void stopServices(String composeFile = 'docker-compose.yml') {
        script.echo "🛑 Stopping Docker Compose services using file: ${composeFile}..."
        script.sh "docker-compose -f ${composeFile} down"
        script.echo "✅ Docker Compose services stopped."
    }

    // 🔍 Method to check the status of Docker Compose services
    void checkStatus(String composeFile = 'docker-compose.yml') {
        script.echo "🔍 Checking Docker Compose services status using file: ${composeFile}..."
        script.sh "docker-compose -f ${composeFile} ps"
        script.echo "✅ Docker Compose services status checked."
    }

    // 📦 Method to rebuild Docker Compose services
    void rebuildServices(String composeFile = 'docker-compose.yml') {
        script.echo "🔄 Rebuilding Docker Compose services using file: ${composeFile}..."
        script.sh "docker-compose -f ${composeFile} up -d --build"
        script.echo "✅ Docker Compose services rebuilt."
    }
}
