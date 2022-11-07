def call(commerceDir, branch, projectRepository) {
    urlPrefix = "https://"
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'githubCodeRepoCredentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {        
        repoDomainPart = projectRepository.substring(urlPrefix.size())
        repository = "https://$USERNAME:$PASSWORD@" + repoDomainPart
        echo "##### Checkout repository #####"
        sh """cd ${commerceDir} && git clone ${repository} . && git fetch --all && git checkout origin/${branch}"""
    }
}