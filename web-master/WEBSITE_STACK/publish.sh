#!/bin/bash

if [ $# -ne 1 ] ; then
	echo "usage: ./publish.sh GFORGE_INRIA_USERNAME (exemple: ./publish.sh alebre)"
	exit 1; 
fi

# Be sure that you have the latest informations before publishing on the official website

cd website 
rsync -av --exclude 'blog' ./. $1@scm.gforge.inria.fr:/home/groups/stack/htdocs/
