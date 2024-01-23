# Jenkins Plugin for Jira Cloud  

Jenkins for Jira provides a free, secure, and reliable way to connect your Jenkins server, running behind the firewall, with either Jira Software Cloud or Jira Service Management Cloud.

This gives your team more visibility and context on every issue in Jira, showing the latest build status or if your work has been successfully deployed to an environment.

You can also use this information to [search across issues using the Jira Query Language (JQL)](https://support.atlassian.com/jira-software-cloud/docs/advanced-search-reference-jql-developer-status/), answering questions like *"Which issues in the current sprint have been deployed to production?"* You can even add these as quick filters on your boards!

Before you begin connecting Jenkins to Jira
-------------------------------------------

Connecting and setting up Jenkins for Jira is a multi-step process that requires admin permissions to multiple platforms: Jira, Jenkins, and in most cases, your team's source code management platform.

The process is not difficult, but you'll need to understand how your team uses Jenkins to make setup decisions along the way.

Because of the range of permissions required, in most cases you'll delegate parts of the connection process to other members of your team, such as a Jenkins admin.

This means that before you begin, it's important to reach out to the members of your team that will use this app and align on how you'll proceed.

Team knowledge required to connect to Jenkins
---------------------------------------------

Each time you connect a server, ask your Jira team the following questions and note their answers - you'll return to these later in the connection process.

1.  Who is the server admin? (Unless you're a Jenkins admin yourself).

2.  Who maintains the Jenkinsfiles relevant to each Jira project?

3.  Does your team want:

    -   Granular control of each pipeline that sends data to Jira, or

    -   Jenkins to send build and deployment updates to Jira every time it runs a pipeline.

Once you know who maintains the Jenkinsfiles for each Jira project, ask those people::

1.  When writing Jenkinsfiles, do you have a consistent naming structure for build and deployment stages?

    -   For example: 

        -   Are *all* build stages named stage('build')?

        -   Are *all* deployment stages named stage('deploy to staging'), stage('deploy to prod'), and so on

2.  If so, what are those structures?

Connect Jenkins to Jira Cloud
-----------------------------

Once you've got the team knowledge required to connect to Jenkins, you'll need to perform - or delegate - the following tasks:

1.  Install Jenkins for Jira on your Jira Cloud site.

2.  Start a connection to Jenkins in Jira.

3.  Install the Atlassian Jira Software Cloud plugin on your Jenkins server.

4.  Complete the connection on your Jenkins server.

5.  Set up the data flow from Jenkins to Jira by editing your server's Jenkinsfiles.

If you're not a Jenkins admin, reach out to your admin before you begin and let them know you'll need their help. Their part in the connection process should take about 15 minutes.

When you've got your Jenkins admin on board, it's time to get started.

### Install Jenkins for Jira on your Jira Cloud site

Permissions necessary: Jira site admin

Install the **Jenkins for Jira (Official)** app via Atlassian Marketplace:

1.  Log in to your Jira site and go to **Apps > Explore more apps**.

2.  Search for the **Jenkins for Jira (Official)** app and select **Get app.**

### Start a connection to Jenkins in Jira

Permissions necessary: Jira site admin

After installing the **Jenkins for Jira** app on your Jira site, you'll need to start a connection to Jenkins.

1.  In Jira, go to **Apps > Manage your apps**.

2.  In the left sidebar, under **Apps**, select **Jenkins for Jira**.

3.  If this is your first connection, you'll be taken to the connection wizard. If you've previously connected a Jenkins server, select **Connect another server** to go to the wizard.

Follow the on-screen prompts in the connection wizard. This experience will vary depending on whether you are a Jenkins admin or a Jenkins admin is helping you, but in either case after giving your server a name, you'll be provided with two credential items: a webhook URL and secret token to use in Jenkins.

To complete the connection process, you or your Jenkins admin will need to log in to Jenkins, set up the server, and use these credentials to complete a connection.

### Install the Atlassian Jira Software Cloud plugin on your Jenkins server

Permissions necessary: Jenkins admin

Once a Jira site admin has started a connection in Jira, you'll need to install the Jira Cloud plugin on Jenkins to complete the connection.

1.  Log in to your Jenkins server and go to **Manage Jenkins > Plugins > Available plugins**.

2.  Search for **Atlassian Jira Software Cloud** and install the plugin.

If you can't find the plugin in the **Available plugins** screen, it may already be installed on your server. To check:

1.  Log in to your Jenkins server and go to **Manage Jenkins > Plugins > Installed plugins**.

2.  Search for **Atlassian Jira Software Cloud.**

3.  If the plugin is already installed, run any available updates.

### Complete the connection on your Jenkins server

Permissions necessary: Jenkins admin

With the **Atlassian Jira Software Cloud** plugin installed on your Jenkins server, and a webhook URL and secret token at hand, you can now complete the connection between Jenkins and Jira.

1.  In Jenkins, go to **Manage Jenkins > Atlassian Jira Software Cloud.**

2.  Select **Add new site.**

3.  Enter your **Jira site name**

4.  Enter your **Webhook URL**

5.  Enter your **Secret** using the Jenkins credential manager.

    1.  Select **Add**

    2.  From the **Kind** dropdown, select **Secret text**

    3.  Paste the secret provided in the Jenkins for Jira connection wizard in the **Secret** field

    4.  Give your secret a descriptive name (such as your site name) in the **Description** field

    5.  Select **Add**

    6.  Use the **Secret** dropdown to select the secret you just entered.

6.  Select **Test connection** to make sure your credentials are valid for your Jira site.

7.  Select **Save**.

8.  If you've successfully entered your site name and credentials, you'll be returned to **Manage Jenkins > Atlassian Jira Software Cloud**.

9.  Refer to [Setup decisions neccessary when connecting Jenkins to Jira Software Cloud](https://support.atlassian.com/jira-software-cloud/docs/setup-decisions-necessary-when-connecting-jenkins-to-jira-software-cloud/), then set up the following options if they are appropriate to your team:

    1.  **Send builds automatically**

    2.  **Send deployments automatically**

10. Select **Save**

If you're a Jenkins admin helping a Jira site admin to complete the connection process, let them know you've completed the connection.

Set up data flow from your Jenkins server to your Jira site by editing Jenkinsfiles
-----------------------------------------------------------------------------------

Permissions necessary: Write access to Jenkinsfiles on your Jenkins server or source code management platform

Once your Jenkins server is connected to Jira, Jira will generate a **set up guide** to help you and your team choose what data flows from the server to Jira. Your team must follow this guide to receive data from Jenkins.

To access the guide:

1.  In Jira, go to **Apps > Manage your apps**

2.  In the left sidebar, under **Apps**, select **Jenkins for Jira**.

3.  Navigate to the server you just selected and open the **Set up guide** tab.

In most cases, you'll delegate the tasks in the guide to other members of your team. The set up guide details how to do this.

How Jenkins for Jira works
--------------------------

Use this reference guide to understand how Jenkins for Jira works or set up data flow without following a set up guide.

The Jenkins for Jira app should give you all the information you need to connect a Jenkins server and get build and deployment data flowing from your server.

The information below comprises a more detailed description of how the app works if you'd like to better understand the options available.

### How Jenkins sends data to Jira

Whenever a pipeline runs in Jenkins, the **Atlassian Jira Software Cloud** plugin will look in the Jenkinsfile describing that pipeline for signs it should send data to Jira.

The plugin looks for two things:

1.  Specific instructions - jiraSendBuildInfo and jiraSendDeploymentInfo - within build or deployment stages.

2.  Specific naming conventions for build and deployment stages (if the plugin has been set up to look for them)

If the plugin finds either of these, it will look for Jira issue keys in the commit messages and branch names of code being built or deployed in those stage, respectively. If it finds these, it will send event data about those stages (such as whether a build was successful or failed) to Jira. If no issue keys are found, the plugin will not send data to Jira.

### How Jira receives data from Jenkins

When your Jira receives event data from Jenkins, the **Jenkins for Jira** app displays relevant event data in your Jira issues, on the deployment pipeline, and in the releases feature. The app discards any events that don't contain issue keys relevant to your site.

### What you need to do

To receive build and deployment data from Jenkins:

-   The Jenkinsfiles describing your server's pipelines must indicate when the **Atlassian Jira Software Cloud** plugin should send data to Jira

-   Your team must include Jira issue keys (e.g. FUSE-123) in their commit messages and branch names so **Jenkins for Jira** knows the event data from Jenkins is relevant to your site

Here's how to set up your Jenkinsfiles to send data to Jira:

Use stage names to send build data to Jira
------------------------------------------

To send build events without having to add specific instructions in your Jenkinsfiles:

1.  Go to **Manage Jenkins > Atlassian Jira Software Cloud**

2.  Enable the checkbox "Sends builds automatically"

When you enable this, the plugin will send an "in progress" build event to Jira once a pipeline run has started and a "success" or "failure" build event once the pipeline has finished successfully or stopped due to an error.

If you also specify a regular expression for builds, the plugin will only send a build event to Jira once a build step with a matching name has been finished.

The regular expression ^build$ would match the build stage in the following Jenkinsfile, for example:

```
pipeline {
    agent  any
    stages {
        stage('build') {
            steps {
                echo  'build done'
            }
        }
    }
}
```

Whenever the pipeline in this Jenkinsfile runs, it will send build events to all configured Jira Cloud sites on start and finish of the build stage.

Use stage names to send deployment data to Jira
-----------------------------------------------

To send build events without having to add specific instructions in your Jenkinsfiles:

1.  Go to **Manage Jenkins > Atlassian Jira Software Cloud**

2.  Enable the checkbox "Sends deployments automatically".

When you enable this, the plugin will send an "in progress" deployment event to Jira once a build step with a name matching the specified regular expression has started, and a "success" or "failure" deployment event once that build step has finished.

For this to work, the deployment steps in your Jenkinsfile must contain the environment name in their name. The regular expression must contain the fragment (?<envName>.*) to match the environment name so that the plugin can extract the environment name from the build step names.

Let's look at an example Jenkinsfile:

```
pipeline {
    agent  any
    stages {
        stage('deployments') {
            parallel {
                stage('deploy to stg') {
                    steps {
                        echo 'stg deployment done'
                    }
                }
                stage('deploy to prod') {
                    steps {
                        echo 'prod deployment done'
                    }
                }
            }
        }
    }
}
```

 If the checkbox "Send deployments automatically" is enabled and the regular expression is set to ^deploy to (?<envName>.*)$, a run of the above Jenkinsfile will send "in progress" deployment events for the stg and prod environments to all configured Jira Cloud sites, followed by respective "success" deployment events once the build steps are finished.

Use explicit instructions to send build data to Jira
----------------------------------------------------

If you want more control over when to send build events, you can use the jiraSendBuildInfo build step:

```
pipeline {
     agent any
     stages {
         stage('Build') {
             steps {
                 echo 'Building...'
             }
             post {
                 always {
                     // previous to version 2.0.0 you must provide parameters to this command (see below)!
                     jiraSendBuildInfo()
                 }
             }
         }
     }
 }
```

This will send a "success" or "failure" build event to all configured Jira Cloud sites after the Build stage has finished successfully or with an error. The Jenkins plugin will automatically extract Jira issue keys from the branch name.

You can also specify a Jira site URL and instruct the plugin to only send build events to this Jira site (rather than to all configured Jira sites).

```
pipeline {
     agent any
     stages {
         stage('Build') {
             steps {
                 echo 'Building...'
             }
             post {
                 always {
                     jiraSendBuildInfo site: 'example.atlassian.net', branch: 'TEST-123-awesome-feature'
                 }
             }
         }
     }
 }
```

Use explicit instructions to send deployment data to Jira
---------------------------------------------------------

If you want more control over when to send deployment events, you can use the jiraSendDeploymentInfo build step:

```
pipeline {
     agent any
     stages {
         stage('Deploy - Staging') {
             when {
                 branch 'master'
             }
             steps {
                 echo 'Deploying to Staging from master...'
             }
             post {
                 always {
                     jiraSendDeploymentInfo environmentId: 'us-stg-1', environmentName: 'us-stg-1', environmentType: 'staging'
                 }
             }
         }
         stage('Deploy - Production') {
            when {
                branch 'master'
            }
            steps {
                echo 'Deploying to Production from master...'
            }
            post {
                always {
                    jiraSendDeploymentInfo environmentId: 'us-prod-1', environmentName: 'us-prod-1', environmentType: 'production'
                }
            }
         }
     }

```

This will send a "success" or "failure" deployment event to all configured Jira sites at the end of the stages Deploy - Staging and Deploy - Production.

You **must** provide the parameters environmentId, environmentName, and environmentType. The environmentType must be one of the following: unmapped, development, testing, staging, production.

You can provide the parameter site to specify to send the deployment events to a single Jira site instead of all configured Jira sites.

When multiple Jira sites are connected to a Jenkins server, the site parameter is required for jiraSendDeploymentInfo with enableGating:true. More details about Deployment Gating can be found [here](https://support.atlassian.com/jira-service-management-cloud/docs/use-deployment-gating-with-jenkins/).

You can also specify a branch with the branch parameter to define the branch from which to extract Jira issue keys to connect the deployments with.

### Example of a complete Jenkinsfile

You can mix build and deployments as in the Jenkinsfile below:

```
pipeline {
     agent any
     stages {
         stage('Build') {
             steps {
                 echo 'Building...'
             }
             post {
                 always {
                     jiraSendBuildInfo site: 'example.atlassian.net'
                 }
             }
         }
         stage('Deploy - Staging') {
             when {
                 branch 'master'
             }
             steps {
                 echo 'Deploying to Staging from master...'
             }
             post {
                 always {
                     jiraSendDeploymentInfo environmentId: 'us-stg-1', environmentName: 'us-stg-1', environmentType: 'staging'
                 }
             }
         }
         stage('Deploy - Production') {
            when {
                branch 'master'
            }
            steps {
                echo 'Deploying to Production from master...'
            }
            post {
                always {
                    jiraSendDeploymentInfo environmentId: 'us-prod-1', environmentName: 'us-prod-1', environmentType: 'production'
                }
            }
         }
     }
 }
```

### View development information in Jira

Whenever you make a commit or merge a pull request in a connected source code management tool, like Bitbucket or GitHub, it should run the Jenkins pipeline you have specified for that repo. The development panel on your Jira issues will update to show any associated build and deployment information, as long as your team is including issue keys in their pull requests, commit messages, and branch names. Learn more about how to [view development information for an issue](https://support.atlassian.com/jira-software-cloud/docs/view-development-information-for-an-issue/).

If you've enabled the deployments feature in your Jira project, the Deployments page will show all your Jenkins deployments on a timeline. You can filter or search to view your deployments by environment, assignee, issue type, and more. And if your team is using releases and versions to organize your work, you'll also find deployment information in the Releases feature.

Link Jira Service Management Cloud with Jenkins
-----------------------------------------------

Learn how to [connect Jenkins to your Jira Service Management cloud project](https://support.atlassian.com/jira-service-management-cloud/docs/link-jira-service-management-with-jenkins/).

Learn more
----------

-   [Create your own integrations between Jira Software Cloud and other on-premise tools](https://developer.atlassian.com/cloud/jira/software/integrate-jsw-cloud-with-onpremises-tools/)
