# Blended Workflow Designer and Engine

This is the code for the Blended Workflow which is a research project, (http://blended-workflow.social-software.esw.inesc-id.pt/), done in the context of INESC-ID's Software Engineering group.

The master contains the current version of development, which includes the blended workflow designer and the blended workflow engine. For the first version, which comprises the engine only, you have to checkout branch blended-workflow-1.0 or the tag v1.0.

The version of the project is a maven project that uses Fénix Framework (https://fenix-framework.github.io/) for persistency support. 

To run the project you have to change some configurations:

-blended-workflow\src\main\config\build.properties (Endpoints, User, password, etc)
    -blended-workflow\src\main\config\log4j (log path file)
