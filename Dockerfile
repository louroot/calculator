FROM ubuntu:latest
RUN \
apt-get update -y && \
apt-get install default-jre -y
ADD ./target/calculator-0.0.1-SNAPSHOT.jar calculator.jar
EXPOSE 8081
CMD java -jar calculator.jar