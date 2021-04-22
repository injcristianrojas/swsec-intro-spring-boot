# Software Security Intro (Spring Boot edition)

# Requirements

* An IDE that supports Maven (Eclipse, IntelliJ IDEA, Visual Studio Code...)
* Java SDK 8
* OWASP ZAP
* Sonarqube
* Docker (optional)

# How to use

Launch from Maven using:

```shell
mvn spring-boot:run
```
And have fun.

# How to do CI-based security testing

These tests cover:

* DAST (OWASP ZAP)
* SAST (Find-Sec-Bugs)
* SCA (OWASP Dependency Check)

## Sonarqube installation

### The Docker way

Build and run the SonarQube machine:

```shell
docker build -t sonarqube .
docker run -p 9090:9090 -p 9000:9000 sonarqube
```

### The standalone way

## Sonarqube setup

Then go to the sonarqube machine (localhost:9000), login as admin/admin, go to
"Quality Profiles", and in the Java section, set "FindBugs Security Audit" as
the default profile (Settings on the right, "Set as default").

## OWASP ZAP

(To be completed)

## Configure and launch

Create a file called `main.properties` which contains the location of the OWASP
ZAP software. For instance:

```
zap.location=C:\Program Files (x86)\OWASP\Zed Attack Proxy
```

Then launch the SAST/DAST/SCA process using:

```shell
mvn
```