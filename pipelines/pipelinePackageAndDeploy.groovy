def codeNumber;

pipeline {
    libraries {
        lib("shared-library@${params.LIBRARY_BRANCH}")
    }
    agent {
        node {
            label 'Built-In'
        }
    }
    options {
        skipDefaultCheckout(true) // No more 'Declarative: Checkout' stage
        timeout(time: 1, unit: 'HOURS') // build should take no more than 1 hour
    }

    stages {
        stage('Prepare') {
            steps{
                script{
                    currentBuild.displayName = "#${currentBuild.number}-${params.PROJECT_TAG}-${params.BUILD_NAME}"
                }
            }
        }

        stage('Build') {
            steps{
                script{
                    codeNumber = buildCommerceCloud("${params.PROJECT_TAG}", "${params.BUILD_NAME}")
                    buildCommerceCloudCheck(codeNumber)
                }
            }
        }

        stage('Deploy') {
            steps{
                script{
                    deploymentCode = commerceCloudDeploy(codeNumber, "${params.DB_UPDATE_MODE}", "${params.ENVIRONMENT_ID}", "${params.DEPLOY_STRATEGY}")
                    commerceCloudDeployCheck(deploymentCode)
                }
            }
        }
    }

    // post build actions
    post {
        failure {
            echo "Issue with build package and deploy"
        }
    }
}
