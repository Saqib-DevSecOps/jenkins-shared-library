# Jenkins Shared Library

This Jenkins Shared Library provides a set of utilities for managing SonarQube quality analysis, Docker operations, Docker Compose deployments, and OWASP Dependency-Check. This `README.md` file outlines how to use the various classes and methods included in the library.

## Table of Contents

- [SonarQube Quality Analysis](#sonarqube-quality-analysis)
- [Docker Management](#docker-management)
- [Docker Compose Deployment](#docker-compose-deployment)
- [OWASP Dependency-Check](#owasp-dependency-check)

## SonarQube Quality Analysis

### Overview

This class provides methods to run SonarQube quality analysis and check the quality gate.

### Methods

- `sonarQubeQualityAnalysis(scanArgs: Map)`:
  - **Description**: Runs SonarQube analysis with specified arguments.
  - **Parameters**:
    - `scanArgs`: Map of additional arguments for the SonarQube scanner (e.g., exclusions).
  
- `checkQualityGate(gateArgs: Map)`:
  - **Description**: Checks the SonarQube quality gate status and optionally aborts the pipeline if the quality gate fails.
  - **Parameters**:
    - `gateArgs`: Map containing `failPipeline` (boolean) to decide whether to abort on failure.

### Usage

```groovy
@Library('your-shared-library') _

pipeline {
    agent any

    stages {
        stage('SonarQube Quality Test') {
            steps {
                sonarQubeQualityAnalysis(
                    scanArgs: [
                        projectName: 'django-notes',
                        projectKey: 'django-notes',
                        additionalArgs: '-Dsonar.exclusions=**/test/**'
                    ]
                )
            }
        }
        
        stage('SonarQube Quality Gate Scan') {
            steps {
                checkQualityGate(
                    gateArgs: [failPipeline: true]
                )
            }
        }
    }
}


## Docker Management

### Overview

This class provides methods for managing Docker operations, including login, build, push, and remove images.

### Methods

- `dockerLogin(config: Map)`:
  - **Description**: Logs into a Docker registry.
  - **Parameters**:
    - `registryUrl`: URL of the Docker registry.
    - `username`: Docker registry username.
    - `password`: Docker registry password.

- `dockerBuild(config: Map)`:
  - **Description**: Builds a Docker image.
  - **Parameters**:
    - `imageName`: Name of the Docker image.
    - `dockerfileDir`: Directory containing the Dockerfile.
    - `tag`: Tag for the Docker image.

- `dockerPush(config: Map)`:
  - **Description**: Pushes a Docker image to a registry.
  - **Parameters**:
    - `imageName`: Name of the Docker image.
    - `tag`: Tag for the Docker image.
    - `registryUrl`: Optional URL of the Docker registry.

- `dockerRemove(config: Map)`:
  - **Description**: Removes the last built Docker image.
  - **Parameters**:
    - `imageName`: Name of the Docker image.
    - `tag`: Tag for the Docker image.

### Usage

```groovy
@Library('your-shared-library') _

pipeline {
    agent any

    stages {
        stage('Docker Login') {
            steps {
                dockerManager.dockerLogin(
                    registryUrl: 'your-registry-url',
                    username: 'your-username',
                    password: 'your-password'
                )
            }
        }
        
        stage('Docker Build') {
            steps {
                dockerManager.dockerBuild(
                    imageName: 'your-image-name',
                    dockerfileDir: '.',
                    tag: 'latest'
                )
            }
        }
        
        stage('Docker Push') {
            steps {
                dockerManager.dockerPush(
                    imageName: 'your-image-name',
                    tag: 'latest',
                    registryUrl: 'your-registry-url'
                )
            }
        }
        
        stage('Remove Last Built Image') {
            steps {
                dockerManager.dockerRemove(
                    imageName: 'your-image-name',
                    tag: 'latest'
                )
            }
        }
    }
}
