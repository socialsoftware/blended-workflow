# Blended Workflow Engine

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
