def call(commerceDir) {
    echo "##### Extract commerce platform ##### -- ${commerceDir}"
    sh "unzip -o ../CXCOM2005*.ZIP -d ${commerceDir}/core-customize"
    /** Uncomment if you will be using the Integration Extension Pack
    echo "##### Extract commerce integration pack #####"
    sh "unzip -o ../CXCOMINTPK2005*.ZIP -d ${commerceDir}/core-customize"
    **/
}  