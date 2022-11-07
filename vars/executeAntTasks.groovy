def call(commerceDir, antTasks, env, antOptions = "-Xmx512m -Dfile.encoding=UTF-8") {
        echo "##### Execute ant tasks : ${antTasks} #####"
        sh "cd ${commerceDir}/core-customize/hybris/bin/platform && . ./setantenv.sh && export ANT_OPTS=\"${antOptions}\" && ant ${antTasks} -Denv=${env}"
} 