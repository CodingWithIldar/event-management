#### Prerequisites:
* docker has to be installed
* docker-compose has to be installed

<br>

> docker build -t event-management:0.0.1 .

Build an image with the application and profile `h2` (by default).

> docker build --build-arg PROFILE=postgres -t event-management:0.0.1 .

Build an image with the application and profile `postgres`, this will enable PostgresSQL as a database.

> docker run -p 8090 event-management:0.0.1

Start a container with the app on a random port, with port forwarding to 8090 (the port in the app settings).

> docker ps -f ancestor=event-management:0.0.1

View a list of containers currently running that were created from the app's image.

> docker stop $(docker ps -q -f ancestor=event-management:0.0.1)

Stop all containers created from the app's image.

> docker-compose up -d

Download/build 2 images (if they don't exist already) - for postgres, and for event-management service,  
then start containers (services). The active profile for the app will be "postgres", which will enable PostgreSQL integration.  
If all containers are running, executing the command second time won't have any effect. `-d` is for the detached mode.

> docker-compose stop

Stops all running containers defined in the current docker-compose.yml.

> docker-compose up --build

If the images already exist but their Dockerfiles or build contexts were changed, rebuilds those images and  
restarts their containers (if they were running). Otherwise, acts the same as `docker-compose up`

> docker-compose up --force-recreate

If only `docker-compose.yml` was changed, `--build` won't take it into account (because no image is rebuilt).  
In this case, `--force-recreate` has to be used, which will recreate the containers.