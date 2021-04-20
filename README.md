# Ohjelmistotekniikka, harjoitustyö
_This is a practice project for university **software engineering** course._


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

### Checkstyle
The style checking is configured in file [checkstyle.xml](https://github.com/annareej/rpg-battlemap/blob/master/RPGBattlemap/pom.xml). The checks are run with command
```
mvn jxr:jxr checkstyle:checkstyle
```
Generated report is located in _target/site/checkstyle.html_ and can be opened with a browser.
