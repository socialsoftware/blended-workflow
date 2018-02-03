# Blended Workflow Designer and Engine

This is the code for the Blended Workflow which is a research project, (https://socialsoftware.github.io/blended/) done in the context of INESC-ID's Software Engineering group.

The master contains the current version of development, which includes the blended workflow designer and the blended workflow engine. For the first version, which comprises the engine only, you have to checkout the tag v1.0 (https://github.com/socialsoftware/blended-workflow/releases/tag/v1.0) and to browse the v1.0 repository go to (https://github.com/socialsoftware/blended-workflow/tree/65f75f73d5e51f8a81f27296322b5b525031fc4d).

The current version of the project is a maven project that uses Fénix Framework (https://fenix-framework.github.io/) for persistency support and Xtex (http://www.eclipse.org/Xtext/) for the designer. 


To run the engine execute: 
- create a "blendedworkflow" database in mysql
- configure the mysql username and password in resources/fenix-framework.properties
- mvn clean spring-boot:run
- access in a browser to localhost:8080

To run the designer:
- in Eclipse import all projets in blended-workflow/designer as "Existing Projects in Workspace"
- "Generate xText Artifacts" for designer/src/pt.ist.socialsoftware.blendedworkflow.designer/BlendedWorkflow.xtext
- run the designer "As Eclipse Application"

To run the frontend (AngularJS):
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

- Start server
	- Change folder to frontend/angular
	```
	npm start
	```

- Open webpage
	```
	http://localhost:8080/
	```

To run the frontend (ReactJS):
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

- Start server
	- Change folder to frontend/react/{branch}
	```
	npm start
	```

- Open webpage
	```
	http://localhost:3000/
	```


Documentation:
- Server ports:
```
frontend angular app - 8081
frontend react app - 3000
engine core api - 8080
```