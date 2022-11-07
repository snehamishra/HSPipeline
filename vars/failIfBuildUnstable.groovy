def call() {
  echo "##### This pipeline should not finish if Quality Gate fails. Setting build status to FAILURE! #####"
  script {
    if (currentBuild.result == 'UNSTABLE') {
      error('Sonar Quality Gate failed!')
    }
  }
}
