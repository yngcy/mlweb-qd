FROM postgres:15.2
RUN localedef -i zh_CN -c -f UTF-8 -A /usr/share/locale/locale.alias zh_CN.UTF-8
ENV LANG zh_CN.utf8
COPY ./create-databases.sh /docker-entrypoint-initdb.d/
# RUN  chmod +x /docker-entrypoint-initdb.d/create-databases.sh