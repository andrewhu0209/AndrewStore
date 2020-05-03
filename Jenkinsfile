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