FROM mysql:8.0.33
ENV LANG C.UTF-8
COPY ./create-databases.sh /docker-entrypoint-initdb.d/
# RUN  chmod +x /docker-entrypoint-initdb.d/create-databases.sh