docker stop auth_srv
docker stop my_postgres
docker rm auth_srv
docker rm my_postgres
docker image rm qd-my_postgres
remove-item -Recurse ./postgres-data