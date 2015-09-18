# Blended Workflow Designer and Engine

This is the code for the Blended Workflow which is a research project, (https://socialsoftware.github.io/blended/) done in the context of INESC-ID's Software Engineering group.

The master contains the current version of development, which includes the blended workflow designer and the blended workflow engine. For the first version, which comprises the engine only, you have to checkout the tag v1.0 (https://github.com/socialsoftware/blended-workflow/releases/tag/v1.0) and to browse the v1.0 repository go to (https://github.com/socialsoftware/blended-workflow/tree/65f75f73d5e51f8a81f27296322b5b525031fc4d).

The current version of the project is a maven project that uses Fénix Framework (https://fenix-framework.github.io/) for persistency support and Xtex (http://www.eclipse.org/Xtext/) for the designer. 

To run the project you have to change some configurations. For the engine:

-blended-workflow\src\main\config\build.properties (Endpoints, User, password, etc)
    
-blended-workflow\src\main\config\log4j (log path file)
