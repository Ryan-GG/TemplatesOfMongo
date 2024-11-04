#!/bin/bash

# Check if a container name is provided
if [ -z "$1" ]; then
  echo "Usage: $0 <container_name>"
  exit 1
fi

# Assign the container name from the first argument
CONTAINER_NAME="$1"

# Execute the mongosh command on the specified container
docker exec "$CONTAINER_NAME" bash -c 'mongosh admin /users/*'
