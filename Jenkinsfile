pipeline {
    agent any
    stages {
        stage("git clone") {
            steps {
                git branch: 'main',
                    url: "https://github.com/nikita200993/project-for-jenkins"
            }
        }
        stage ("build") {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('SonarQube') {
            steps {
                step {
                    def scannerHome = tool 'MySonar';
                    withSonarQubeEnv('MySonar') {
                        sh "${scannerHome}/bin/sonar-scanner -X \
                        -Dsonar.projectKey=nikitaaero:freestyle \
                        -Dsonar.sources=src/main \
                        -Dsonar.java.binaries=target/classes"
                    }
                }
            }
        }
        stage('AllureReport') {
            steps {
                allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                ])
            }

        }
        stage('Ansible') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'mfti', passwordVariable: 'pass')]) {
                  sh 'echo $pass'
                  sh '#!/bin/bash \
                      set -e \
                      cd ${WORKSPACE}/ansible \
                      ansible-playbook deploy.yml --vault-password-file $pass'
                }
            }
        }
    }
}
