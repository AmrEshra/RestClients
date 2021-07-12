// start of pipeline
pipeline{
	// where pipeline job will run
	agent any //{ label 'Windows_Slave' }
	// start of stages : build, test, deploy ...
	tools {
    	maven 'Maven 3.6.1'
    	jdk 'JDK 8'
  	}
	stages{
		// start of stage : build
		stage('maven'){
			steps{                
	            // invoke command to build with maven
				sh "mvn clean install"
			}
		}
		
		// start of stage : sonarQube Analysis
		// stage('sonarQubeAnalysis') {
		// 	environment {
		//         scannerHome = tool 'SonarQubeScanner'
		//     }
		//     steps {                 
	    //         // start SonarQube Analysis   
	    //         withSonarQubeEnv('SonarQube') { sh '${scannerHome}/bin/sonar-scanner'}
	            	                 
		//     }
		//  }
	}
}
