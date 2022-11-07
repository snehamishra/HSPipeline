# Jenkins Sample Pipeline for SAP Commerce Cloud

## Description
This project provides a sample of how to create a generic template for Jenkins CI projects with SAP Commerce Cloud. The Pipelines are defined as [Scripted Pipeline](https://www.jenkins.io/doc/book/pipeline/syntax/#scripted-pipeline). It means that they are written in Groovy.

## Jenkins Configuration
- add Global Shared Library - with name "shared-library"
- add Node - configure node with label "subordinate"
- create credentials to github repository with id "githubCodeRepoCredentials"
- create credentials to SAP Commerce Cloud with id commerceCloudCredentials (for building and deploying using APIs to SAP Commerce Cloud; with username as subscription ID and password as API token)
- create credentials for Sonar and add it to Sonar Plugin

### Jenkins Plugin
- [Hidden Parameter](https://wiki.jenkins.io/display/JENKINS/Jenkins+Hidden+Parameter+Plugin) - This plugin adds support for Parameter. After the plugin is installed,in job configuration's page,you can see Hidden Parameter.
- [Extensible Choice Parameter](https://plugins.jenkins.io/extensible-choice-parameter/) - This plugin adds "Extensible Choice" as a build parameter.You can select how to retrieve choices, including the way to share choices among all jobs.
- [GitHub Pull Request Builder](https://plugins.jenkins.io/ghprb/) - This plugin builds pull requests in github and report results.
- [Environment Dashboard](https://plugins.jenkins.io/environment-dashboard/) - This Jenkins plugin creates a custom view which can be used as a dashboard to display which code release versions have been deployed to which test and production environments (or devices).
- [SonarQube Scanner](https://plugins.jenkins.io/sonar/) - This plugin allow easy integration of SonarQube™, the open source platform for Continuous Inspection of code quality.
- [Job DSL](https://plugins.jenkins.io/job-dsl/) -  The Job DSL plugin attempts to solve this problem by allowing jobs to be defined in a programmatic form in a human readable file.
- [Masked Password](https://plugins.jenkins.io/mask-passwords/) - This plugin allows masking passwords that may appear in the console, including the ones defined as build parameters. 
- [Pipeline Utility Steps](https://plugins.jenkins.io/pipeline-utility-steps/) - Small, miscellaneous, cross platform utility steps for Jenkins Pipeline jobs.

## How to start?

NOTE: The sonarqube artifact only works with commerce cloud version 2005 currently as there was a problem with the Sonar ant task that is provided with previous versions, so if Sonar is desired to be run, then please the project must use a 2005 commerce release.

1. To start using this project use customization script. To process run:

```
. ./customize.sh
```

The script will replace placeholders for you. You can review dsl/builder.groovy to ensure all the fields you want committed to your repository are set correctly. These will become the default parameters for your pipeline jobs.
2. Commit and push the changes to the repository you'll be using for managing your Jenkins configurations
3. In order to use the "Build every day" job, you need to place the right SAP Commerce artifact in the root workspace folder of the machine that will be doing the builds using the naming convention from Download Center (e.g. CXCOM2005-*.ZIP)
4. If you will be using the Integration Extension Packs for Core Commerce 2005+ you will need to uncomment the lines in vars/extractCommerce.groovy and also include the zip file in your workspace (same place you put the CXCOM zip file subordinate/workspace/CXCOM200500P_3-70004955.ZIP
5. In Jenkins, click the "New Item" option from the left toolbar
6. Give it a name to identify the job that will be used to build out the seed jobs, select 'pipeline' and click 'ok'
7. On the next screen scroll down to the 'pipeline' section and select "Pipeline script from SCM". Fill in the remaining details to point to the repository you commited your jenkins configs to in step #5
8. Once the job is created, build it. It will generate all the seed jobs in the /pipelines folder
9. Determine which pipeline job you wish to run and execute, filling in any parameters that differ from default

### More details
For more info please see the article on continuous delivery on [CX Works](https://www.sap.com/cxworks/expert-recommendations/articles/commerce-cloud)
