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
                sh " docker stop $(docker ps -a -q)"
                sh "docker run -p 80:80 -d andrewstore:${env.BUILD_ID}"
            }
            post {
                success {
                    echo '開始存檔...'
                    sh "whoami"
                    sh "pwd"
                    archiveArtifacts artifacts: '**/target/*.jar'
                }
            }
        }


    }
}