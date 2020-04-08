#!/bin/bash

curl -X POST -H "Content-Type: application/json" \
 -d '{
	"controller": "false",
	"restController": "false",
	"service": "false",
	"repository": "false",
	"component": "true"
	}'\
 http://localhost:9001/actuator/chaosmonkey/watchers

echo

echo "Activating component watcher"

echo

