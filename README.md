# Ohjelmistotekniikka, harjoitustyö
_This is a practice project for university **software engineering** course._

It is common for Dungeon Masters or Game Master running tabletop roleplaying games (TTRPG) to simulate and keep track combat encounters with a battlemap or grid. Grid usually is made from 5x5 ft. squares. Player and non player characters are usually marked with tokens or miniatures.  

The software will generated a grid of squares with given width and heigth. Tokens can be created to grid and can be moved with mouse and arrow keys. 


## Project
[Requirements specification](https://github.com/annareej/rpg-battlemap/blob/master/documentation/requirements.md)

[Architecture](https://github.com/annareej/rpg-battlemap/blob/master/documentation/Architecture.md)

[Work hours](https://github.com/annareej/rpg-battlemap/blob/master/documentation/workhours.md)

## Commands
### Tests
Tests are run with command
```
mvn test
```
Report for test coverage is generated with command 

```
mvn jacoco:report
```
Generated report is located in _target/site/jacoco/index.html_ and can be opened with a browser.

### Jar file
An executable jar file is generated with command
```
mvn package
```
Generated jar file is located in _target/_ and is named _RPGBattlemap-1.0-SNAPSHOT.jar_ and is excecuted with command

```
java -jar RPGBattlemap-1.0-SNAPSHOT.jar
```

### Javadoc
Javadoc can be generated with command
```
mvn javadoc:javadoc
```
Generated document is located in _target/site/apidocs/index.html_

### Checkstyle
The style checking is configured in file [checkstyle.xml](https://github.com/annareej/rpg-battlemap/blob/master/RPGBattlemap/pom.xml). The checks are run with command
```
mvn jxr:jxr checkstyle:checkstyle
```
Generated report is located in _target/site/checkstyle.html_ and can be opened with a browser.
