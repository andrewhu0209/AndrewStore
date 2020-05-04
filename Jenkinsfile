pipeline {
    agent any

    tools{
        maven 'local maven'
    }


    triggers {
         pollSCM('H 0 * * *')
     }

     stages{
     

        	stage('Build'){
            	steps('Maven build') {
                	sh 'mvn clean package'
                	sh "docker build . -t andrewstore:${env.BUILD_ID}" 
            	}
				post {
               		success {
                    echo '開始存檔...'
                    archiveArtifacts artifacts: '**/target/*.jar'
                	}
            	}
	
        	}

			stage('Checkstyle') {
                    	steps {
                        	sh "mvn checkstyle:check"
                        	recordIssues(tools: [checkStyle(reportEncoding: 'UTF-8')])
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