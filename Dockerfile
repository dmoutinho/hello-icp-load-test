FROM maven:3.5.2
COPY . .
RUN mvn clean install
CMD java -Dserver.port=9080 -jar  target/hello-icp-load-test-0.0.1-SNAPSHOT.jar
EXPOSE 9080