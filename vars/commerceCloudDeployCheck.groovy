def call(deployCode) {
    script {
        while (true) {
          wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${token}", var: 'PASSWD']]]) {
              result = sh (script: "curl --location --request GET 'https://portalrotapi.hana.ondemand.com/v2/subscriptions/${subscriptionId}/deployments/$deployCode' --header 'Authorization: Bearer ${token}'",returnStdout:true)
          }
          echo "$result"
          statusResult = readJSON text: "$result"

          if("DEPLOYED".equals(statusResult["status"])) {
            break;
          }

          if("FAIL".equals(statusResult["status"])) {
            error("Deployment was not completed successfully on SAP Commerce Cloud")
          }

          sh('sleep 120s')

        }

        echo "Commerce Cloud Deploy Complete"
    }
}  