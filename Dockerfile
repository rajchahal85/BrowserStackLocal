FROM openjdk:11-jre-slim-bullseye

WORKDIR /usr/share/dockersel

RUN apk add chromium

ADD target/selenium-docker.jar selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs libs

ADD testng.xml testng.xml

ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/* -DBROWSER=$BROWSER -DWEB_URL=$WEB_URL org.testng.TestNG testng.xml