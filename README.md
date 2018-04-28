# SER 516 • Project 3 • Team 09

## Building

```bash
git clone https://github.com/SER516/ProjectThree_Team09
cd ProjectThree_Team09
mvn package
```

## Running

The `mvn package` command will build the client into an executable fat JAR file that includes the library dependencies. To execute the client, simply double-click on the JAR file in `target/` or run the following command in a terminal window.

```bash
java -jar target/project3-1.0-jar-with-dependencies.jar
```
## Import Project on Eclipse
File >> Import >> Maven >> Existing Maven Projects >> Browse to Project Folder(Directory with pom.xml) 

## Code Coverage

To run the unit tests and generate a code coverage report, run

```bash
mvn cobertura:cobertura
```

This will generate a static HTML site in `target/site/`. Open `target/site/index.html` in a web browser to view the code coverage report.

## Team Members

Team Members:
1.  Yathartha Goel (33, ygoel@asu.edu)
2.  Garv Mathur (72, gmathur@asu.edu)
3.  Abhinab Mohanty (77, amohant7@asu.edu)
4.  Aayushi Shah (99, amshah@14@asu.edu)
5.  Zain Siddiqui (105, zsiddiq2@asu.edu)
6.  Vishakha Singal (106, vsingal1@asu.edu)
7.  Varun Srivastava (107, vsriva12@asu.edu)
8.  Manish Tandon (111, mtandon3@asu.edu)
9.  Naga Ravi Teja Thoram (114, nthoram@asu.edu)
10. Adhiraj Tikku (115, atikku1@asu.edu)
