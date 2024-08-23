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
