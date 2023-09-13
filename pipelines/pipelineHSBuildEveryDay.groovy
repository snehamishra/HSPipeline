def relativeJunitLogsPath = 'core-customize/hybris/log/junit'
def projectDir = 'hybris'

pipeline {
    libraries {
        lib("shared-library@${params.LIBRARY_BRANCH}")
    }
//    agent {
//        node {
//            label 'Built-In'
//        }
//    }
    triggers {
        cron('H 18 * * *')
    }
    options {
        skipDefaultCheckout(true) // No more 'Declarative: Checkout' stage
    }

    stages {
        stage('Prepare') {
            steps {
                script {
                    projectDir = "${WORKSPACE}"
                }
                cleanWs()
                checkoutRepository("${projectDir}", "${params.PROJECT_TAG}", "${params.PROJECT_REPO}")
                extractCommerce(projectDir)
            }
        }

        stage('Platform Setup') {
            steps {
                script {
                    executeCommerceBuild(projectDir)
                }
            }
        }

        stage('Run sonarqube') {
            steps {
                sonarqubeCheck("${BUILD_TAG}_develop", projectDir, "${params.SONAR_REPO_NAME}", "${params.SONAR_URL}") // Pipeline status is set as UNSTABLE if Sonar Quality Gate fails but build is SUCCESSFUL
                failIfBuildUnstable() // Fails build if Quality Gate fails
            }
        }

        stage('Run all tests') {
            steps {
                executeAntTasks(projectDir, "yunitinit alltests -Dtestclasses.packages=${params.PACKAGE_TO_TEST}", 'dev')
            }
        }
    }

    // post build actions
    post {
        always {
            junit "${relativeJunitLogsPath}/*.xml"
        }
    }
}
