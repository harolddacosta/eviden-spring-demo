#!/bin/bash

docker run --name eviden-api \
	-d -p 9001:9090 \
	--add-host=host.docker.internal:host-gateway \
	-e SPRING_PROFILES_ACTIVE=docker \
	eviden-api:latest && \
	docker logs -f eviden-api
