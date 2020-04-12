pipeline {
    agent any

    options {
        skipStagesAfterUnstable()
	    gitLabConnection('gitlab.com')       
  	    gitlabBuilds(builds: ['Buid','Run-app','Test','Deliver'])  
    }

    environment {
       JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests '       
            }

            post {     
      		    failure {                     
          		    updateGitlabCommitStatus name: 'Build', state: 'failed' 
        	    }
                success {                     
          		    updateGitlabCommitStatus name: 'Build', state: 'success' 
        	    }
            }
        }

        stage('Run-app') {
            steps {
		        sh 'docker-compose down'
      		    echo "Running tests in a fully containerized environment..."
                sh 'docker-compose up -d'
            }

            post {     
      		    failure {                     
          		    updateGitlabCommitStatus name: 'Run-app', state: 'failed' 
        	    }
                success {                     
          		    updateGitlabCommitStatus name: 'Run-app', state: 'success' 
        	    }
            }
        }

        stage('Test') { 
            steps {
                withPythonEnv('/usr/bin/python3.6') {
                // Creates the virtualenv before proceeding
                    sh './install-tools.sh'
		    
                    sh './watchers-scripts/activate-all-watcher.sh'
                    sh './chaos-expérimentations/experiments.sh'
                    sh './watchers-scripts/disactivate-all-watcher.sh'
                    }
                }

            post {     
      		    failure {                     
          		    updateGitlabCommitStatus name: 'Run-app', state: 'failed' 
        	    }
                success {                     
          		    updateGitlabCommitStatus name: 'Run-app', state: 'success' 
        	    }
            }    
        }

        stage('Deliver') { 
            steps {
                sh 'echo dilevry step' 
            }

        post {     
      		    failure {                     
          		    updateGitlabCommitStatus name: 'Deliver', state: 'failed' 
        	    }
                success {                     
          		    updateGitlabCommitStatus name: 'Deliver', state: 'success' 
        	    }
            }  
        }
    }
}
