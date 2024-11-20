# Use the MySQL image version 8.3 (enables the native password plugin)
FROM mysql:8.3

# Set the working directory
WORKDIR /tmp

# Copy the world.sql file from your project directory into the container's initialization directory
COPY db/world-db/world.sql /docker-entrypoint-initdb.d/

# Set the root password for MySQL
ENV MYSQL_ROOT_PASSWORD example

