#! /bin/sh

docker stop auth_srv
docker stop my_postgres
docker rm auth_srv
docker rm my_postgres
# docker image rm qd-auth_srv
docker image rm qd-my_postgres
echo 123 | sudo -S rm -Rf ./postgres-data