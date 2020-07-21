# Software Security Intro (Spring Boot edition)

## How to use

Launch using:

```shell
mvn spring-boot:run
```
And have fun.

## Tests

These cover:

* DAST (OWASP ZAP)
* SAST (Find-Sec-Bugs)
* 

First, build and run the SonarQube machine:

```shell
docker build -t sonarqube .
docker run -p 9090:9090 -p 9000:9000 sonarqube
```

Then go to the sonarqube machine (localhost:9000), login as admin/admin, go to
"Quality Profiles", and in the Java section, set "FindBugs Security Audit" as
the default profile (Settings on the right, "Set as default").

Make sure that the `zapPath` parameter is set in the POM for OWASP ZAP's
location, then:

```shell
mvn
```