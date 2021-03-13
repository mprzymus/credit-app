FROM postgres
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB db
EXPOSE 5432
COPY schema.sql /docker-entrypoint-initdb.d/