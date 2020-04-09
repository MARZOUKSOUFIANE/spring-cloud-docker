pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        }
    environment {
       JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
        }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests '
                 
            }
        }
        stage('Run-app') {
            steps {
                //sh 'mvn test'
                sh 'docker-compose up'
            }
        }
        stage('Test-chaos') { 
            steps {
                withPythonEnv('/usr/bin/python3.6') {
                // Creates the virtualenv before proceeding
                    sh 'pip install chaostoolkit'
                    sh 'pip install -U chaostoolkit-spring'
                    sh 'chaos --version'
                    sh './watchers-scripts/activate-all-watcher.sh'
                    sh './chaos-expérimentations/experiments.sh'
                    sh './watchers-scripts/disactivate-all-watcher.sh'
                    }
                }
            }
        stage('Deliver') { 
            steps {
                sh 'echo dilevry step' 
            }
        }
    }
}
