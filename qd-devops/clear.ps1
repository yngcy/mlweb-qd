docker stop auth_srv
docker stop mysql
docker rm auth_srv
docker rm mysql
docker image rm qd-mysql
remove-item -Recurse ./mysql-data