pipeline {
    agent any

    tools{
        maven 'local maven'
    }


    triggers {
         pollSCM('* * * * *')
     }

     stages{
        stage('Build'){
            steps {
                sh 'mvn clean package'
                sh "docker build . -t andrewstore:${env.BUILD_ID}"
                
            }

        }
        
        stage ('Deployments'){
        	steps{
        	    sh "docker container stop \$(docker ps -qa)"
                sh "docker run -p 80:80 -d andrewstore:${env.BUILD_ID}"
        	}

        
        
        }


    }
}