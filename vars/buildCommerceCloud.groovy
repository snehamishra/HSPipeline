def call(branch, buildName) {
    echo "##### Initiate Build to SAP Commerce Cloud Environment #####"
    //deploy tag 
    script{
        withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'commerceCloudCredentialsHS', usernameVariable: 'subscriptionId', passwordVariable: 'token']]) {
            build = sh (script: "curl --location --request POST 'https://portalapi.commerce.ondemand.com/v2/subscriptions/${subscriptionId}/builds' --header 'Content-Type: application/json' --header 'Authorization: Bearer ${token}' --header 'Content-Type: text/plain' --data-raw '{\"branch\": \"${branch}\",\"name\": \"${buildName}\"}'",returnStdout:true)
            echo "$build"
            build_result = readJSON text: "$build"
            code_number = build_result["code"]
            return code_number
        }
    }
}
