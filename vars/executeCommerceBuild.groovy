def call(commerceDir) {
	echo "##### Execute install script ##### -- ${commerceDir}"
	addProperty(commerceDir, "solrserver.instances.default.autostart=false")
	sh "cd ${commerceDir}/core-customize/hybris/bin/platform && . ./setantenv.sh && ant clean all"
} 
   