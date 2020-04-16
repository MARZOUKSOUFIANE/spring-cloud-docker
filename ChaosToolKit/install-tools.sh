#!/bin/bash

python -c 'import pkgutil; exit(1 if pkgutil.find_loader("chaostoolkit") else 0)'

if [ $? = 0 ]
then
        echo "chaostoolkit not exist"
	echo " installing chaos toolkit"

	pip install chaostoolkit

	echo "--------------------------------------------------------------"

	echo " installing chaostoolkit-spring driver"
	pip install -U chaostoolkit-spring

	echo "--------------------------------------------------------------"

	echo " verify installation version"
	chaos --version

else 
	echo "chaostoolkit  exist"
fi
