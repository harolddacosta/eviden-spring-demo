FROM eclipse-temurin:17

# Start of docker image creation
ENV HTTP_PORT 9090
ENV CONTEXT_PATH api

ENV PROXY_OPTS "" 
ENV REGION_OPTS -Duser.timezone=UTC -Duser.language=en
ENV JAVA_OPTS ""

RUN echo 'root:Docker!' | chpasswd

#RUN addgroup --gid 1024 microservice
RUN useradd -U app -s /usr/sbin/nologin -d /home/app -m

RUN curl -L -o /usr/bin/jq https://github.com/stedolan/jq/releases/download/jq-1.6/jq-linux64
RUN chmod +x /usr/bin/jq

USER app:app
WORKDIR /home/app

# Different from other dockerfiles
RUN mkdir certificates

COPY target/demo-0.0.1-SNAPSHOT.jar .
COPY scripts/entrypoint.sh .

RUN sed -i 's/\r$//' entrypoint.sh
RUN ["chmod", "+x", "entrypoint.sh"]

EXPOSE ${HTTP_PORT}
HEALTHCHECK --interval=5s CMD curl -f http://localhost:$HTTP_PORT/$CONTEXT_PATH/v1/ping | jq '.status' || exit 1

ENTRYPOINT ["./entrypoint.sh"]
