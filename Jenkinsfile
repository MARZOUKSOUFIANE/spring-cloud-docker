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
		sh 'docker-compose down'
      		echo "Running tests in a fully containerized environment..."
                sh 'docker-compose up -d'
		sh 'bash wait-for-it.sh http://localhost:9001/commandes -- echo service commande is up'
            }
        }
        stage('Test-chaos') { 
            steps {
                withPythonEnv('/usr/bin/python3.6') {
                // Creates the virtualenv before proceeding
                    sh 'pip install chaostoolkit'
                    sh 'pip install -U chaostoolkit-spring'
                    sh 'chaos --version'
                    sh 'bash watchers-scripts/activate-all-watcher.sh'
                    sh 'bash chaos-expérimentations/experiments.sh'
                    sh 'bash watchers-scripts/disactivate-all-watcher.sh'
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
