#!/usr/bin/env groovy


import com.example.DockerComposeManager

def startServices(Map config = [:]) {
    def dockerCompose = new DockerComposeManager(this)
    dockerCompose.startServices(config.composeFile as String)
}

def stopServices(Map config = [:]) {
    def dockerCompose = new DockerComposeManager(this)
    dockerCompose.stopServices(config.composeFile as String)
}

def checkStatus(Map config = [:]) {
    def dockerCompose = new DockerComposeManager(this)
    dockerCompose.checkStatus(config.composeFile as String)
}

def rebuildServices(Map config = [:]) {
    def dockerCompose = new DockerComposeManager(this)
    dockerCompose.rebuildServices(config.composeFile as String)
}
