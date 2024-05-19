FROM ubuntu:22.04

WORKDIR /app

RUN apt-get update
RUN apt-get install -y kotlin

COPY entrypoint.sh /usr/local/bin/entrypoint.sh

ENTRYPOINT /usr/local/bin/entrypoint.sh
