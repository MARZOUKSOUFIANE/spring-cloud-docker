#!/bin/bash


echo "Begining experiments"

echo

chaos run service-produit-latence-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run service-produit-exception-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run service-produit-custom-exception-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run service-produit-kill-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run service-commande-latence-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run service-commande-kill-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

echo

echo "End of experiments , success"





