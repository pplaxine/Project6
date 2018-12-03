# Project6 - Climbing site

This webapp allows the users to share and discuss everything related to climbing. 

It is possible to create its own site, indicating all the features, for users to discuss about.

Also, this webapp includes a topos renting system to make booking for owners and renters as easy as few clicks.  

## Getting Started

  #### DataBase
    1. Start PGAdmin 4 and select the database you have created for the purpose. 
    2. Via the query tool, browse the following database creation script : 03_ESCALADE_BDD_SETUP-V2.sql . 
    3. Execute the script. 
    Your database is now ready to go. 

  #### WebApp
    1. Start your tomcat serveur. 
    2. To access Tomcat Manager App, go to http://localhost:8080/ and select Manager App. 
    3. In section WAR file to deploy, browse the escalade-webapp.war you have downloaded, and select deploy.
    4. You can now run the webapp via the following url : http://localhost:8080/escalade-webapp/home

## Prerequisites

Install Java JRE version 8 or higher.

Install PGAdmin4 and create, for the webapp, a corresponding database (for more information : https://www.pgadmin.org/). 

Install the latest version of Tomcat and set configuration to unlock access to the Manager app(for more information : https://tomcat.apache.org/tomcat-9.0-doc/manager-howto.html). 

## Built With

* [Eclipse](https://www.eclipse.org/documentation/)

## Authors

* **Philippe Plaxine** - *Initial work* - [PPlaxine](https://github.com/pplaxine)
