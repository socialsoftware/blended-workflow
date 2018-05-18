# Blended Workflow Designer, Engine and Verifier

This is the code for the Blended Workflow which is a research project, (https://socialsoftware.github.io/blended/) done in the context of INESC-ID's Software Engineering group.

The master contains the current version of development, which includes the blended workflow  verifier, designer and engine. For the first version, which comprises the engine only, you have to checkout the tag v1.0 (https://github.com/socialsoftware/blended-workflow/releases/tag/v1.0) and to browse the v1.0 repository go to (https://github.com/socialsoftware/blended-workflow/tree/65f75f73d5e51f8a81f27296322b5b525031fc4d).

The current version of the project is a maven project that uses Fénix Framework (https://fenix-framework.github.io/) for persistency support, Xtex (http://www.eclipse.org/Xtext/) for the designer, and Alloy (http://alloy.mit.edu/alloy/) for the verifier.

To run the verifier you have to install Alloy, http://alloy.mit.edu/alloy/download.html, and open the files in blended-workflow/verifier/.

To run the engine, atart by deciding which to run. Therea are two engines, a core and a resources mananagement, where the latter includes the core. 
To run the engine: 
- install mysql, and define a database, either "core" or "resources", depending on the engine what want to run
- configure the mysql username and password in blended-workflow/engine/{CHOSEN_ENGINE}/src/main/resources/fenix-framework.properties
- access the respective subfolder blended-workflow/engine/{CHOSEN_ENGINE}
- mvn clean spring-boot:run
- access in a browser to localhost:8080

To run the designer:
- in Eclipse import all projets in blended-workflow/designer as "Existing Projects in Workspace"
- "Generate xText Artifacts" for designer/src/pt.ist.socialsoftware.blendedworkflow.designer/BlendedWorkflow.xtext
- run the designer "As Eclipse Application"
- compile examples of specifications that are in blended-workflow/designer/models/

There are two frontends, AngularJS and React. In both cases it is necessary to install nodeJS
- Install nodeJS
	- MacOs
	```
	$ brew install node
	```
	- Unix
	```
	$ sudo apt-get update
	$ sudo apt-get install nodejs
	$ sudo apt-get install npm
	```

To run the AngularJS frontend
- move to folder blended-workflow/frontend/angular
- $ npm start
- access http://localhost:8081/

To run the ReactJS frontend
- $ npm install react-scripts
- $ npm start
- access http://localhost:3000/

Documentation:
- Server ports:
```
frontend angular app - 8081
frontend react app - 3000
engine core or resources api - 8080
```
