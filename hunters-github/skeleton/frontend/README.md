#### Getting Started with Create React App

#### Keycloak Arquitecture


#### This is a project that authenticates against Keycloak.
#### For this to function, you need to deploy a Keycloak server. You can deploy from this reference architecture project.
#### Go to ../bootstrap/docker and run the command: # docker-compose up -d postgres keycloak


#### First you need to configure Keycloak Server. If you have already configured, you only need to put clientId and realm inside "/src/shared/constant.ts". Otherwise: 

#### Go to http://localhost:8095 and enter with these credentials: 
	@usename: admin
	@pwd: hunters3


#### Configure a REALM, CLIENT and USER.

 #### Create a new Realme
 #### Go to Clients panel and Add new client
 #### Set clientID: privatesection or whatever you want
 #### ROOT URL: http://localhost:3000/  or wherever you are going to deploy
 #### Hit the save button.

 #### In the Clients panel, press on Installation TAB. Now download your JSON with Keycloak-ODIC-JSON format option.
 #### Replace the constant keycloakConfig in this project at "src/shared/constants.ts" modifying cliendId, realm and resource.

 #### Create a new User at Users tab.
 #### Give a username & hit save button.
 #### Go to credentials tab and set a password for user created and switch off temporary password.
 #### With this creedentials you will be able to log in into this application.


#### Back to the project, now install all the dependencies from the packages.json
 
 	npm install

#### You can run the tests with the following command:

 	npm run test 

#### Or run the project:

 	npm run start

 #### When you run this project, the first page you will see is the authentication page. (Remember to log in with your User credentials created before).
 #### After successful authentication , you can navigate into different sections.
 #### You can see the state of the store with "Redux - devtools plugin" (available in Chrome web store). Download here: https://chrome.google.com/webstore/detail/redux-devtools/lmhkpmbekcpmknklioeibfkpmmfibljd?hl=es 
 #### Once installed, go to the developer tools section in Chrome and find the Redux tab. Now you will see the changes in the status of your application with this plugin (e.g. update the date in the Altia private section) 



#### In this archetype, the basic dependencies necessary for project implementation have been installed. Thus, it provides authentication against Keycloak. To use it in the components, contexts must be used, there are some examples at demo/components.

#### Some of the installed utilities are: keycloak ( authentication ), axios ( http client ), configured redux ( global state ), dayjs ( library for dates ): https://github.com/iamkun/dayjs/ and latest version of react, typescript, react-scripts. Also includes configuration of service workers.

#### To find accessibility issues it is recommended to use the eslint-plugin-jsx-a11y library, a guide can be found at this website: https://www.npmjs.com/package/eslint-plugin-jsx-a11y.

#### In addition, it can be combined with the Wave browser extension. To install, visit this website: https://wave.webaim.org/extension/


#### Respect the folder structure.