FROM openjdk:17.0.2-jdk AS builder

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait

ADD ./ /src
RUN cd /src && ./gradlew clean build install
#RUN rm -fR /src

FROM openjdk:17.0.2
COPY --from=builder /wait /wait
COPY --from=builder /src/build/install/schedules /schedules

ENTRYPOINT /wait && cd /schedules/bin && ./schedules