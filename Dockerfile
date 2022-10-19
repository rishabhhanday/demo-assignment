FROM gradle AS builder
COPY ./ ./
RUN gradle bootJar
CMD java -jar build/libs/demo-0.0.1-SNAPSHOT.jar