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
		sh 'docker-compose down'
      		echo "Running tests in a fully containerized environment..."
                sh 'docker-compose up -d'
            }
        }
        stage('Test') { 
            steps {
                withPythonEnv('/usr/bin/python3.6') {
                // Creates the virtualenv before proceeding
		    sh 'pip uninstall -y chaostoolkit'
                    sh './install-tools.sh'
		    
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
