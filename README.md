# Camp-Application-and-Management-System
**Docs:** [UML Class Diagram](https://github.com/zhihaohong52/Camp-Application-and-Management-System/blob/main/UML_CAMS.jpg) |
[Java Docs](https://zhihaohong52.github.io/Camp-Application-and-Management-System/)

SC2002 Object Oriented Project Group Assignment AY2023-24 Sem 1

A Java-based application for managing camps for students in NTU. It features authentication, user management, camp management, and communications between students, camp committee members, and staff. This README file provides instructions on how to clone, compile, and run the project.

## Table of Contents

- [CAMS setup instructions](#cams-setup-instructions)
  - [Compiling and Running the project](#compiling-and-running-the-project)
    - [Using the terminal](#using-the-terminal)
    - [Using Eclipse](#using-eclipse)
  - [Generating JavaDocs](#generating-javadocs)
    - [Using the terminal](#using-the-terminal-1)
    - [Using Eclipse](#using-eclipse-1)
- [Usage](#usage)
  - [Login Credentials](#login-credentials)
 
# CAMS setup instructions

## Compiling and Running the project

### Using the terminal

These setup instructions will guide you through the process of cloning the repository, navigating to the cloned repository, compiling the project, and running the project in your terminal.

1. Open your terminal

2. Clone the repository by entering the following command:

   ```bash
   git clone https://github.com/zhihaohong52/Camp-Application-and-Management-System.git
   ```

3. Navigate to the cloned repository by entering the following command:

   ```bash
   cd Camp-Application-and-Management-System/camp_application_and_management_system
   ```

4. Compile the project by entering the following command:

   ```bash
   javac -cp src -d bin src/main/CAMS.java
   ```

5. Run the project by entering the following command:

   ```bash
   java -cp bin main.CAMS
   ```
Congratulations, you have successfully cloned, compiled, and run the FYPMS project!

### Using Eclipse

If you prefer to use Eclipse as your IDE, you can also set up the project there. Here are the steps you need to follow:

1. Open Eclipse
2. Click on `File` > `Import` > `Git` > `Projects from Git` > `Clone URI`
3. In the `Clone URI` window, paste the following URL:

   ```bash
   [https://github.com/xJQx/sc2002-fypms.git](https://github.com/zhihaohong52/Camp-Application-and-Management-System.git)
   ```

4. Click `Next` and follow the prompts to finish the cloning process
5. Once the project is cloned, right-click on the project folder and select `Properties`
6. In the `Properties` window, click on `Java Build Path` > `Source` > `Add Folder`
7. Select the `src` folder from the project directory and click `OK`
8. Now you can run the project by right-clicking on `CAMS.java` in the `src/main` folder and selecting `Run As` > `Java Application`

That's it! You should now have the project up and running in Eclipse.

## Generating JavaDocs

### Using the terminal

Follow the steps below to generate JavaDocs using the terminal:

1. Open you terminal.
2. Navigate to the root directory of the project.
3. Run the following command in the terminal:

   ```bash
    javadoc -d camp_application_and_management_system/docs -sourcepath camp_application_and_management_system/src -subpackages controllers:enums:interfaces:main:model:services:stores:util:view -private -author -version
   ```

   This command will generate the JavaDocs and save them in the docs directory in HTML format.

4. Navigate to the `docs` directory using the following command:

   ```bash
   cd docs
   ```

5. Open the `index.html` file in a web browser to view the generated JavaDocs.

Congratulations, you have successfully created and viewed the JavaDocs.

### Using Eclipse

1. Open the Eclipse IDE and open your Java project.

2. Select the package or class for which you want to generate JavaDocs.

3. Go to the "Project" menu and select "Generate Javadoc".

4. In the "Generate Javadoc" dialog box, select the "Private" option to generate JavaDocs for private classes and members.

5. Choose the destination folder where you want to save the generated JavaDocs.

6. In the "Javadoc command line arguments" field, add any additional arguments that you want to include, such as `-classpath`.

7. Click the "Finish" button to start the JavaDocs generation process.

8. Once the JavaDocs have been generated, you can view them by opening the `index.html` file in your web browser.

Congratulations, you have successfully created and viewed the JavaDocs.

# Usage

## Login Credentials

This section contains some login credentials for users with different roles. The full list is available in `data/user.csv` file.

**Students:**

```bash
# Student 1
USERID: YCHERN
PASSWORD: password

# Student 2
USERID: KOH1
PASSWORD: password

# Student 3
USERID: BR015
PASSWORD: password
```

**Staff:**

```bash
# Supervisor 1
USERID: HUKUMAR
PASSWORD: password

# Supervisor 2
USERID: OURIN
PASSWORD: password
```
