#!/bin/bash

if [ $# -ne 1 ] ; then
	echo "usage: ./publish.sh GFORGE_INRIA_USERNAME (exemple: ./publish.sh alebre)"
	exit 1; 
fi

cd _site
rsync -av ./. $1@scm.gforge.inria.fr:/home/groups/stack/htdocs/blog/.
