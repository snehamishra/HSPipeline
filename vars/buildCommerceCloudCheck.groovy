def call(codeNumber) {
    script {
        while (true) {
          withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'commerceCloudCredentialsHS', usernameVariable: 'subscriptionId', passwordVariable: 'token']]) {
              result = sh (script: "curl --location --request GET 'https://portalapi.commerce.ondemand.com/v2/subscriptions/${subscriptionId}/builds/$codeNumber' --header 'Authorization: Bearer ${token}'",returnStdout:true)
          }
          echo "$result"
          statusResult = readJSON text: "$result"

          if("SUCCESS".equals(statusResult["status"])) {
            break;
          }

          if("FAIL".equals(statusResult["status"])) {
            error("Build was not completed successfully on SAP Commerce Cloud")
          }

          sh('sleep 120s')

        }

        echo "Commerce Cloud Build Complete"
    }
}  
