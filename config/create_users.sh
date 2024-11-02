#!/bin/bash

#If you see not primary, then the container is not ready yet
docker exec mongo-primary bash -c 'mongosh admin /users/*'
