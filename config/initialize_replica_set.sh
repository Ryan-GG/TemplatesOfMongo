#!/bin/bash

# Check if a container name is provided
if [ -z "$1" ]; then
  echo "Usage: $0 <container_name>"
  exit 1
fi

# Assign the container name from the first argument
CONTAINER_NAME="$1"

# Initialize the replica set
docker exec -it "$CONTAINER_NAME" mongosh --eval '
rs.initiate({
    _id: "rs0",
    members: [
        { _id: 0, host: "mongo-primary:27017" },
        { _id: 1, host: "mongo-secondary1:27017" },
        { _id: 2, host: "mongo-secondary2:27017" }
    ]
})'
