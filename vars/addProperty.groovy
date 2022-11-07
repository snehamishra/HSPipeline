def call(commerceDir, property){
    sh("echo '${property}' >> ${commerceDir}/core-customize/hybris/config/local.properties")
}
