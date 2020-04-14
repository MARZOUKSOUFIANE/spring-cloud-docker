#!/bin/bash


echo "Begining experiments"

echo

chaos run chaos-expérimentations/service-produit-latence-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run chaos-expérimentations/service-produit-exception-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run chaos-expérimentations/service-produit-custom-exception-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run chaos-expérimentations/service-produit-kill-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run chaos-expérimentations/service-commande-latence-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

chaos run chaos-expérimentations/service-commande-kill-experiment-extended.json

echo "--------------------------------------------------------------"
echo "--------------------------------------------------------------"

echo

echo "End of experiments"





