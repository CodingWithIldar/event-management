> docker build -t event-management:0.0.1 .

Build an image with the application.

> docker run -p 8090 event-management:0.0.1

Start a container with the app on a random port, with port forwarding to 8090 (the port in the app settings).

> docker ps -f ancestor=event-management:0.0.1

View a list of containers currently running that were create from the app's image.

> docker stop $(docker ps -q -f ancestor=event-management:0.0.1)

Stop all containers created from the app's image.