pipeline {
    agent any

    options {
        skipStagesAfterUnstable()
	    gitLabConnection('gitlab-connection')       
  	    gitlabBuilds(builds: ['Build','Install-tools','Run-app','Test-api','Test-container','Deliver'])  
    }

    environment {
       JAVA_HOME = '/usr/lib/jvm/java-8-openjdk-amd64'
    }

    triggers {
        gitlab(triggerOnPush: true, triggerOnMergeRequest: true, branchFilterType: 'All')
    }

    stages {

        stage('Build') {
            steps {
                gitlabBuilds(builds: ['Build']) {
                    gitlabCommitStatus('Build'){ 
                        script {
                            sh 'mvn clean package -DskipTests '       
                        }
                    }
                }
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

        stage('Install-tools') { 

            steps {
                gitlabBuilds(builds: ['Test']) {
                    gitlabCommitStatus('Test'){
                        withPythonEnv('/usr/bin/python3.6') {
                            dir('Ansible'){
                                script {
                                    wrap ([$class: 'AnsiColorBuildWrapper', 'colorMapName':'XTerm']) {
                                        ansiblePlaybook(
                                            playbook: 'chaostk-playbook.yml',
                                            colorized: true,
                                            inventory: 'inventory.ini'
                                        )
                                    }
                                }
                            }
                        } 
                    }
                } 
            }

            post {     
      		    failure {                     
          		    updateGitlabCommitStatus name: 'Install-tools', state: 'failed' 
        	    }
                success {                     
          		    updateGitlabCommitStatus name: 'Install-tools', state: 'success' 
        	    }
            }
        }

        stage('Run-app') {

            steps {
                gitlabBuilds(builds: ['Run-app']) {
                    gitlabCommitStatus('Run-app'){
                        script {
                            sh 'docker-compose down'
                            echo "Running tests in a fully containerized environment..."
                            sh 'docker-compose up -d'
                        }
                    }
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

        stage('Test-api') { 

            steps {
                gitlabBuilds(builds: ['Test-api']) {
                    gitlabCommitStatus('Test-api'){
                        script {
                            withPythonEnv('/usr/bin/python3.6') {
                               // Creates the virtualenv before proceeding
                                dir('ChaosToolKit') {
                                    sh './watchers-scripts/activate-all-watcher.sh'
                                    sh './chaos-expérimentations/experiments.sh'
                                    sh './watchers-scripts/disactivate-all-watcher.sh'
                                    sh 'chaos report --export-format=html5 journal.json report.html'
                                }
                            }     
                        }
                    }
                }  
            }

            post {     
      		    failure {                      
          		    updateGitlabCommitStatus name: 'Test-api', state: 'failed' 
        	    }
                success {                     
          		    updateGitlabCommitStatus name: 'Test-api', state: 'success' 
        	    }
            }    
        }


        stage('Test-container') { 

            steps {
                gitlabBuilds(builds: ['Test-container']) {
                    gitlabCommitStatus('Test-container'){
                        script {
                            pumba kill produits     
                        }
                    }
                }  
            }

            post {     
      		    failure {                      
          		    updateGitlabCommitStatus name: 'Test-container', state: 'failed' 
        	    }
                success {                     
          		    updateGitlabCommitStatus name: 'Test-container', state: 'success' 
        	    }
            }    
        }

        stage('Deliver') { 

            steps {
                gitlabBuilds(builds: ['Deliver']) {
                    gitlabCommitStatus('Deliver'){
                        script {
                            sh 'echo dilevry step' 
                        }
                    }
                }
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
