#!/bin/bash

echo "waiting for docker containers"

sleep 150

curl -X POST -H "Content-Type: application/json" \
 -d '{
	"controller": "true",
	"restController": "true",
	"service": "true",
	"repository": "true",
	"component": "true"
	}'\
 http://localhost:9001/actuator/chaosmonkey/watchers

echo

echo "Activating all watcher"

echo

