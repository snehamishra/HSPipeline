def call(commerceDir) {
	echo "##### Building JS application #####"
	sh "cd ${commerceDir}/js-storefront/spartacus-ssr"
	echo "##### Executing [yarn, install] #####"
	sh "cd ${commerceDir}/js-storefront/spartacus-ssr&& npm install -g yarn && yarn install"
	echo "##### Executing [yarn, build:ssr-stg] #####"
	sh "cd ${commerceDir}/js-storefront/spartacus-ssr && ng update --all && yarn run build:ssr-stg"
	echo "##### Executing dist folder commit #####"
	sh "cd ${commerceDir}/js-storefront/spartacus-ssr/dist && git add . && git commit -m 'Push from jenkins' && git push"
} 
   