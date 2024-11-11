# Setting up mongodb using docker:

1. install docker image from docker hub

```
docker pull mongodb/mongodb-community-server:latest
```

2. create the container from the image

```
docker run --name mongodb -p 27017:27017 -d mongodb/mongodb-community-server:latest
```

3. check that the container is running

```
docker container ls       #doesn't show stopped containers

docker ps -a              #shows all containers
```

4. connect to the container database shell (if you're not using compass)

```
docker exec -it mongodb mongosh
```

5. stop the container

```
docker stop mongodb
```

6. start the container

```
docker start mongodb
```

## Mongodb cheatsheet

https://gist.github.com/bradtraversy/f407d642bdc3b31681bc7e56d95485b6
