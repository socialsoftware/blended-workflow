# Blended Workflow Designer and Engine

This is the code for the Blended Workflow which is a research project, (http://blended-workflow.social-software.esw.inesc-id.pt/), done in the context of INESC-ID's Software Engineering group.

The master contains the current version of development. For the first version, which comprises the engine only, you have to download blended1.0.

-Required Software:
--Java
--YAWL
--Tomcat (optional if the Tomcat embedded in YAWL is used)

-YAWL:
--Install YAWL 2.3
--Activate Worklet service
--Launch YAWL

-Blended Workflow:
--Download Blended Workflow from GITHub.
--Change configuration files:
    -blended-workflow\src\main\config\build.properties (Endpoints, User, password, etc)
    -blended-workflow\src\main\config\log4j (log path file)
--Compile and generate .war file
--Deploy in a Tomcat server (can use YAWL Tomcat)
--Access the aplication through the browser
