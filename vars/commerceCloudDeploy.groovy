def call(buildName, dbUpdateMode, environmentId, strategy) {
    echo "##### Initiate Deployment to SAP Commerce Cloud Environment #####"
    //deploy tag 
    script{
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'HarrisScarfeCloudCredentials', usernameVariable: 'subscriptionId', passwordVariable: 'token']]) {
            deploy = sh (script: "curl --location --request POST 'https://portalrotapi.hana.ondemand.com/v2/subscriptions/${subscriptionId}/deployments' --header 'Content-Type: application/json' --header 'Authorization: Bearer ${token}' --header 'Content-Type: text/plain' --data-raw '{\"buildCode\": \"${buildName}\",\"databaseUpdateMode\": \"${dbUpdateMode}\",\"environmentCode\": \"${environmentId}\",\"strategy\": \"${strategy}\"}'",returnStdout:true)
            echo "$deploy"
            deploy_result = readJSON text: "$deploy"
            deploy_code = deploy_result["code"]
            return deploy_code
        }
    }
}  