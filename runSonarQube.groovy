def call() {
  withSonarQubeEnv('SonarQube') {
          script {
              def scannerHome = tool 'SonarQube'
              withCredentials([usernamePassword(credentialsId: 'sonarqube-credentials', usernameVariable: 'sonarUsername', passwordVariable: 'sonarPassword')]) {
                  sh "${scannerHome}/bin/sonar-scanner " +
                     "-Dsonar.host.url=http://192.99.35.61:9000 " +
                     "-Dsonar.projectKey=test1 " +
                     "-Dsonar.sources=. " +
                     "-Dsonar.exclusions=vendor/**,resources/**,**/*.java " +
                     "-Dsonar.login=$sonarUsername " +
                     "-Dsonar.password=$sonarPassword"
              }
          }
      }}
