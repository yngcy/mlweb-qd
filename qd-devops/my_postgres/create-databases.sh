#!/bin/bash
set -e
set -u

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
--	CREATE USER db1;
	CREATE DATABASE auth_db;
	CREATE DATABASE qd_db;
EOSQL
