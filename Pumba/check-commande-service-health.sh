#!/bin/bash

url="http://localhost:9002/commandes/3/product"
status_code=$(curl -o /dev/null -isv --head --write-out '%{http_code}\n' $url)

if [ $status_code == 200 ]
then
        echo "service commande is UP"
else
        echo "service commande is down"
fi
