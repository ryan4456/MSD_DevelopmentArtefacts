# MSD_DevelopmentArtefacts
This is the development artefacts of the second assignment of Mobile System Development.
## 01_Refined Design Document.pdf
The design document is redefined according to the the comments from Assignment 1. Ther are mainly two places modified.

1. The Scope section is too wide in the previous version. I narrowed it down to make it more specific and achievable. 
2. The evaluation criteria are not enough, so I added three more test cases to evaluate the application, and the application will become more robust. 

## 02_Software Requirements Specification.pdf
The software requirements specification is the document generated in the requirement stage of the Waterfall Process

## 03_iOS_Project
In order to build the project, the following software needs to be installed. 
1. Xcode (Version 11.7)
2. HBuilder X (Version 2.8.13.20200927)

After the above software is installed, following the steps below to build the iOS project. 
1. Open the Project Folder `03_iOS_Project` with HBuilder X.
2. Choose the menu `Run/Run on Devices or Simulator/iOS Simulator/iPhone 11`.
3. The source code will be compiled and run on iPhone 11 simulator.


## 04_API_Project
In order to build the project, the following software needs to be installed. 
1. JDK (Adopt Open JDK 8.0)
2. IntelliJ IDEA (Version ULTIMATE 2019.3.4)
3. Maven (Version 3.6.1)
4. Tomcat (Version 9.0.34) 

After the above software is installed, following the steps below to build the backend project. 
1. Open the Project with IntelliJ IDEA.
2. Open the Terminal, enter the directory `server` by typing `cd ./server`.
3. Execute `mvn package` to generate the war file of the project.
4. Copy the file `vtl-server.war` in the directory `server/target` to the `webapps` directory of Tomcat.
5. Run the Tomcat web container by typing the command `./startup.sh` in terminal.
