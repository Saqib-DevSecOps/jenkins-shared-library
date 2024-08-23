#!/usr/bin/env groovy

import com.exarth.jenkinslibrary.DockerManager


def dockerLogin(Map config = [:]) {
    def docker = new DockerManager(this)
    docker.dockerLogin(config.registryUrl as String, config.username as String, config.password as String)
}

def dockerBuild(Map config = [:]) {
    def docker = new DockerManager(this)
    docker.dockerBuild(config.imageName as String, config.dockerfileDir as String, config.tag as String)
}

def dockerPush(Map config = [:]) {
    def docker = new DockerManager(this)
    docker.dockerPush(config.imageName as String, config.tag as String, config.registryUrl as String)
}

def dockerRemove(Map config = [:]) {
    def docker = new DockerManager(this)
    docker.dockerRemove(config.imageName as String, config.tag as String)
}
