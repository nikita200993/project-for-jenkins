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
                withMaven {
                    sh 'mvn clean package'
                }
            }
        }
    }
}
