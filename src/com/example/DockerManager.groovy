#!/usr/bin/env groovy

package com.example


class DockerManager implements Serializable {

    def script

    DockerManager(script) {
        this.script = script
    }

    // 🛠️ Method for Docker login
    void dockerLogin(String registryUrl, String username, String password) {
        script.echo "🔐 Logging in to Docker registry: ${registryUrl}..."
        script.sh "echo ${password} | docker login ${registryUrl} -u ${username} --password-stdin"
        script.echo "✅ Logged in to Docker registry."
    }

    // 🏗️ Method for Docker build
    void dockerBuild(String imageName, String dockerfileDir = '.', String tag = 'latest') {
        script.echo "🔨 Building Docker image: ${imageName}:${tag} from directory: ${dockerfileDir}..."
        script.sh "docker build -t ${imageName}:${tag} ${dockerfileDir}"
        script.echo "✅ Docker image built: ${imageName}:${tag}."
    }

    // 🚀 Method for Docker push to ECR or Docker Hub
    void dockerPush(String imageName, String tag = 'latest', String registryUrl = '') {
        def fullImageName = registryUrl ? "${registryUrl}/${imageName}:${tag}" : "${imageName}:${tag}"
        script.echo "🚀 Pushing Docker image: ${fullImageName}..."
        script.sh "docker push ${fullImageName}"
        script.echo "✅ Docker image pushed: ${fullImageName}."
    }

    // 🗑️ Method for removing the last built Docker image
    void dockerRemove(String imageName, String tag = 'latest') {
        script.echo "🗑️ Removing Docker image: ${imageName}:${tag}..."
        script.sh "docker rmi ${imageName}:${tag} || true" // '|| true' to avoid errors if image not found
        script.echo "✅ Docker image removed: ${imageName}:${tag}."
    }
}
