pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package'
            }
        }
        stage('deploy'){
        	steps{
        		sh 'cp /home/ec2-user/.jenkins/workspace/Bank_P0/target/Bank_P0.war /home/ec2-user/apache-tomcat-9.0.40/webapps'
        	}
        }
    }    
}
