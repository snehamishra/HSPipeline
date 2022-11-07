#!/bin/bash

# utils
function pretty {
    printf "\n##### ${1} \n"
}

pretty "Starting customizing CI project..."
pretty "Enter Commerce project repository URL (e.g. \"https://github.com/SAP-samples/cloud-commerce-sample-setup\" ) :"
read projectRepo

pretty "Enter Jenkins CI repository URL (e.g. \"https://github.com/your_organisation/jenkins-project\" ) :"
read pipelineRepo

pretty "Enter Sonar URL (e.g. \"http://localhost:9000\" ) :"
read sonarUrl

pretty "Enter package to test (e.g. \"com.projectName.*\" ):"
read packageToTest

pretty "Replacing placeholders..."

projectRepoName=${projectRepo##*/}
projectRepo="${projectRepo}.git"
pipelineRepoName="${pipelineRepo}.git"

sed -i '' "s#\${PROJECT_REPO}#$projectRepo#" dsl/builder.groovy
sed -i '' "s#\${PIPELINE_REPO}#$pipelineRepoName#" dsl/builder.groovy
sed -i '' "s#\${SONAR_URL}#$sonarUrl#" dsl/builder.groovy
sed -i '' "s#\${SONAR_REPO_NAME}#$projectRepoName#" dsl/builder.groovy
sed -i '' "s/\${PACKAGE_TO_TEST}/$packageToTest/" dsl/builder.groovy

pretty "Congratulations! Your CI project for: \"$projectRepoName\" is ready to use!" 


