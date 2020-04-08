#!/bin/bash

curl -X POST -H "Content-Type: application/json" \
 -d '{
	"controller": "false",
	"restController": "false",
	"service": "true",
	"repository": "false",
	"component": "false"
	}'\
 http://localhost:9001/actuator/chaosmonkey/watchers

echo

echo "Activating service watcher"

echo

