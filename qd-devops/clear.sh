#! /bin/sh

docker stop auth_srv
docker stop mysql
docker rm auth_srv
docker rm mysql
# docker image rm qd-auth_srv
docker image rm qd_mysql
echo 123 | sudo -S rm -Rf ./mysql-data