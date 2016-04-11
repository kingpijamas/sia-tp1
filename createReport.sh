#!/bin/bash

pandoc ./Informe.md -o  Informe.pdf --toc --number-sections  -V papersize:"a4paper"
#echo '________________________________________________________'
# echo 'Revisando errores de ortografía en el informe'
# aspell list --lang=es < ./drafts/Informe.md
echo '----Creado el informe'

