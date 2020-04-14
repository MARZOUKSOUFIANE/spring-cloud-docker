#!/bin/bash

echo "waiting for docker containers"

sleep 150

curl -X POST -H "Content-Type: application/json" \
 -d '{
	"controller": "false",
	"restController": "true",
	"service": "false",
	"repository": "false",
	"component": "false"
	}'\
 http://localhost:9001/actuator/chaosmonkey/watchers

echo 

echo "Activating rest controller watcher"

echo

